package com.lxs.graduate.service;

import com.lxs.graduate.entity.Order;

public interface OrderService {

    public int addOrder(Order order);

    public int deleteOrder(Integer id);

    public int updateOrder(Order order);

    public Order findOrderById(Integer id);

    public Order findAllOrder();
}
