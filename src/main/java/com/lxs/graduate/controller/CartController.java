package com.lxs.graduate.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lxs.graduate.entity.Cart;
import com.lxs.graduate.entity.Msg;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.CartService;
import com.lxs.graduate.service.CartServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/cart")
@Slf4j
public class CartController {

    @Autowired
    CartServiceImpl cartService;


    /**
     * 加入购物车
     * @return
     */
    @GetMapping(value = "/add")
    public String addCart(@RequestParam Integer productId, @RequestParam int num, ModelMap model){
        UserDetails user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        //获取登陆用户的userId
       // 加入购物车
       int effectNum = cartService.addCart(((User) user).getId(),productId,num);
        if (effectNum<=0){
            Msg msg=new Msg("购物车信息","添加购物车失败",null);
            model.addAttribute("message",msg);
        }
        else{
            Msg msg=new Msg("购物车信息","添加购物车成功",null);
            model.addAttribute("message",msg);
        }
        return "notices";
    }

    @GetMapping(value = "/getCartList")
    public String getCartList(ModelMap modelMap){
        UserDetails user= (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Cart> cartList = cartService.getCartList(((User) user).getId());
        for(Cart str : cartList) {//其内部实质上还是调用了迭代器遍历方式，这种循环方式还有其他限制，不建议使用。
            System.out.println(str.toString());
        }
//        modelMap.addAttribute("lists",cartList);
        return "carts";
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

    @PostMapping("/delCartProduct")
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
