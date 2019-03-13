package com.lxs.graduate.controller;

import com.lxs.graduate.entity.Msg;
import com.lxs.graduate.entity.Order;
import com.lxs.graduate.entity.Product;
import com.lxs.graduate.entity.User;
import com.lxs.graduate.service.CartServiceImpl;
import com.lxs.graduate.service.OrderService;
import com.lxs.graduate.service.ProductService;
import com.lxs.graduate.util.DateUtil;
import com.lxs.graduate.util.IdUtil;
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
    IdUtil idUtil;


    @Autowired
    DateUtil dateUtil;



    @RequestMapping("/toOrder")
    public String toOrder(@RequestParam Integer pId, @RequestParam Integer orderNum, @RequestParam double price, ModelMap model){
        model.addAttribute("pId",pId);
        model.addAttribute("orderNum",orderNum);
        model.addAttribute("orderMoney",price*orderNum);
        return "/order/addOrder";
    }


    //交易逻辑：先生成订单，修改商品状态为待交易，删除购物车
    @RequestMapping("/addOrder")
    public String addOrder(Order order, ModelMap model) throws ParseException {
        Product p=productService.findProductById(order.getpId());
        IdUtil idUtil=new IdUtil();
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        order.setId(idUtil.getOrderId(user.getId(),p.getId()));
        order.setBuyId(user.getId());
        order.setSellId(p.getUserId());
        order.setOrderStatus("待交易");
        order.setOrderTime(dateUtil.getCurrentDate());
        order.setPayStatus("取消订单");
        int orderId=orderService.addOrder(order);
        cartService.delCartProduct(user.getId(),p.getId());
        Product product=productService.findProductById(order.getpId());
        product.setpStatus("待交易");
        productService.updateProduct(product);
        Msg msg=new Msg("订单信息","订单生产成功，请及时到交易地点完成交易！","订单号为："+order.getId());
        model.addAttribute("message",msg);
        return "notices";
    }

    @RequestMapping("/pay")
    public String pay(ModelMap model){
        Msg msg=new Msg("支付信息","购买成功",null);
        model.addAttribute("message",msg);
        return "notices";
    }

    @RequestMapping("/finishOrder")
    public String finishOrder(@RequestParam Long id, ModelMap model){
        Order order=orderService.findOrderById(id);
        orderService.updateOrder(order);
        order.setOrderStatus("交易成功");
        Msg msg=new Msg("支付信息","交易成功",null);
        model.addAttribute("message",msg);
        return "notices";
    }



    @RequestMapping("/getAllOrders")
    public String getOrder(ModelMap map){
        List<Order> list=orderService.findAllOrders();
        map.put("orders",list);
        return "/allOrders";
    }


}
