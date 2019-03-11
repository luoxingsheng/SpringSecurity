package com.lxs.graduate.controller;

import com.lxs.graduate.entity.Msg;
import com.lxs.graduate.entity.Order;
import com.lxs.graduate.entity.Product;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.CartServiceImpl;
import com.lxs.graduate.service.OrderService;
import com.lxs.graduate.service.ProductService;
import com.lxs.graduate.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    ProductService productService;


    @Autowired
    OrderService orderService;


    @Autowired
    CartServiceImpl cartService;


    @Autowired
    DateUtil dateUtil;

    @RequestMapping("/toOrder")
    public String toOrder(@RequestParam Integer pId, @RequestParam Integer orderNum, @RequestParam double price, ModelMap model){
        model.addAttribute("pId",pId);
        model.addAttribute("orderNum",orderNum);
        model.addAttribute("orderMoney",price*orderNum);
        return "/order/addOrder";
    }

    @RequestMapping("/addOrder")
    public String addOrder(Order order, ModelMap model) throws ParseException {
        System.out.println("订单数目"+order.getOrderNum());
//        Order order=new Order();
        Product p=productService.findProductById(order.getpId());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setBuyId(user.getId());
        order.setSellId(p.getUserId());
        order.setOrderStatus("1");
        order.setOrderTime(dateUtil.getCurrentDate());
        order.setPayStatus("1");
        System.out.println(order.toString());
        orderService.addOrder(order);
        cartService.delCartProduct(user.getId(),p.getId());
        model.addAttribute("orders",order);
        return "/order/pay";
    }

    @RequestMapping("/pay")
    public String pay(ModelMap model){
        Msg msg=new Msg("支付信息","购买成功",null);
        model.addAttribute("message",msg);
        return "notices";
    }


    @RequestMapping("/getAllOrders")
    public String getOrder(ModelMap map){
        List<Order> list=orderService.findAllOrders();
        map.put("orders",list);
        return "/allOrders";
    }

    @RequestMapping("/getOrderByBuyId")
    public String getOrderByBuyId(ModelMap map){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> list=orderService.findOrderByBuyId(user.getId());
        map.put("orders",list);
        return "/users/myOrder";
    }

    @RequestMapping("/getOrderBySellId")
    public String getOrderBySellId(ModelMap map){
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        List<Order> list=orderService.findOrderBySellId(user.getId());
        map.put("orders",list);
        return "/users/sellOrder";
    }
}
