package com.lxs.graduate.service;

import com.lxs.graduate.entity.Order;

import java.util.List;

public interface OrderService {

    public int addOrder(Order order);

    public int deleteOrder(Integer id);

    public int updateOrder(Order order);

    public Order findOrderById(Integer id);

    public List<Order> findAllOrders( );

    public List<Order> findOrderByBuyId(Integer id);

    public List<Order> findOrderBySellId(Integer id);


}
