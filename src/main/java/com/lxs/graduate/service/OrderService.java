package com.lxs.graduate.service;

import com.lxs.graduate.entity.Order;

import java.util.List;

public interface OrderService {

    public int addOrder(Order order);

    public int deleteOrder(Long id);

    public int updateOrder(Order order);

    public Order findOrderById(Long id);

    public List<Order> findAllOrders( );

    public List<Order> findOrderByBuyId(Integer id);

    public List<Order> findOrderBySellId(Integer id);

    public List<Order> findUnfinishedOrders(Integer id );


}
