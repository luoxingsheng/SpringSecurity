package com.lxs.graduate.controller;

import com.lxs.graduate.entity.*;
import com.lxs.graduate.service.CartServiceImpl;
import com.lxs.graduate.service.OrderService;
import com.lxs.graduate.service.ProductService;
import com.lxs.graduate.service.UserService;
import com.lxs.graduate.util.BadWordUtil;
import com.lxs.graduate.util.DateUtil;
import com.lxs.graduate.util.IdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    UserService userService;

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
        order.setOrderScore(3);//
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
        order.setOrderStatus("交易成功");
        orderService.updateOrder(order);
        Msg msg=new Msg("交易信息","交易成功",null);
        model.addAttribute("message",msg);
        return "notices";
    }



    @RequestMapping("/getAllOrders")
    public String getOrder(ModelMap map){
        List<Order> list=orderService.findAllOrders();
        map.put("orders",list);
        return "/allOrders";
    }

    /**
     *Order rating,The score is 1 - 5 points, corresponding to -2 - 2 points,
     * the order will affect the seller's credit points.
     * Reputation credits will serve as a way to evaluate a user's reputation.
     * @param id
     * @param score
     * @return
     */
    @ResponseBody
    @PostMapping(value = "/order_score")
    public Map<String,Object> leaveWord(@RequestParam("id")Long id,
                                        @RequestParam("score")Integer score) {
        Map<String,Object> map = new HashMap<>();
        Order order = orderService.findOrderById(id);
        order.setOrderScore(score);
        order.setOrderStatus("评分成功");
        orderService.updateOrder(order);
        User user = userService.getUserById(order.getSellId());
        Integer user_score =user.getCreditScore()+score-3;
        //The credit score is 100 points, and when it exceeds 100 points, it still shows 100 points.
        if(user_score>= 100){
            user.setCreditScore(100);
            userService.updateUser(user);
        }else{
            user.setCreditScore(user_score);
            userService.updateUser(user);
        }
        System.out.println(user_score);
        Msg msg = new Msg("Rating information", "Thank you for your evaluation.", null);
        map.put("message",msg);
        return map;
        }


}
