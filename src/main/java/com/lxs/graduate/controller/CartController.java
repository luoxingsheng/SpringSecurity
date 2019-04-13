package com.lxs.graduate.controller;

import com.lxs.graduate.entity.Cart;
import com.lxs.graduate.entity.Msg;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.CartServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    CartServiceImpl cartService;


    /**
     * Add to Shopping Cart
     */
    @GetMapping(value = "/add")
    public String addCart(@RequestParam Integer productId, @RequestParam int num, ModelMap model){
        UserDetails user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //Get the userId of the logged in user
       // add to Shopping Cart
       int effectNum = cartService.addCart(((User) user).getId(),productId,num);
        if (effectNum<=0){
            Msg msg=new Msg("Shopping cart information","fail to add in order cart",null);
            model.addAttribute("message",msg);
        }
        else{
            Msg msg=new Msg("Shopping cart information","Add shopping cart successfully",null);
            model.addAttribute("message",msg);
        }
        return "notices";
    }

    /**
     * Get a list of shopping carts
     */
    @GetMapping(value = "/getCartList")
    public String getCartList(ModelMap modelMap){
        UserDetails user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Cart> cartList = cartService.getCartList(((User) user).getId());
        modelMap.addAttribute("lists",cartList);
        return "/products/carts";
    }

    @PostMapping(value = "/updateCartNum")
    public String updateCartNum( @RequestParam Integer productId, @RequestParam int num, ModelMap model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            int effectNum = cartService.updateCartNum(user.getId(),productId,num);
        if (effectNum <=0){
            Msg msg=new Msg("购物车信息","更新购物车失败",null);
            model.addAttribute("message",msg);
        }
        else{
            Msg msg=new Msg("购物车信息","更新购物车成功",null);
            model.addAttribute("message",msg);
        }
        return "notices";
    }

    @PostMapping(value = "/checkAll")
    public String checkAll(@RequestParam String check,ModelMap model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int effectNum = cartService.checkAll(user.getId(),check);
        if (effectNum <=0){
            Msg msg=new Msg("购物车信息","全选购物车失败",null);
            model.addAttribute("message",msg);
        }else{
            Msg msg=new Msg("购物车信息","全选购物车成功",null);
            model.addAttribute("message",msg);
        }

        return "notices";
    }

    @GetMapping("/delCartProduct")
    public String delCartProduct(@RequestParam Integer productId,ModelMap model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int effectNum = cartService.delCartProduct(user.getId(),productId);
        if (effectNum <=0){
            Msg msg=new Msg("购物车信息","删除商品失败",null);
            model.addAttribute("message",msg);
        }else{
            Msg msg=new Msg("购物车信息","删除商品成功",null);
            model.addAttribute("message",msg);
        }
        return "notices";
    }

    @PostMapping("/delCart")
    public String delCart(ModelMap model){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int effectNum = cartService.delCart(user.getId());
        if (effectNum <=0){
            Msg msg=new Msg("购物车信息","删除购物车失败",null);
            model.addAttribute("message",msg);
        }else{
            Msg msg=new Msg("购物车信息","删除购物车成功",null);
            model.addAttribute("message",msg);
        }
        return "notices";
    }

}
