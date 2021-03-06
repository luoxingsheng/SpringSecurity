package com.lxs.graduate.service;

import com.lxs.graduate.dao.OrderMapper;
import com.lxs.graduate.entity.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

    @Autowired
    OrderMapper orderMapper;

    @Override
    public int addOrder(Order order) {
        return orderMapper.insert(order);
    }

    @Override
    public int deleteOrder(Long id) {
        return deleteOrder(id);
    }

    @Override
    public int updateOrder(Order order) {
        return orderMapper.updateByPrimaryKey(order);
    }

    @Override
    public Order findOrderById(Long id) {
        return orderMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Order> findAllOrders() {
        return orderMapper.findAllOrders();
    }

    @Override
    public List<Order> findOrderByBuyId(Integer id) {
        return orderMapper.findOrderByBuyId(id);
    }

    @Override
    public List<Order> findOrderBySellId(Integer id) {
        return orderMapper.findOrderBySellId(id);
    }

    @Override
    public List<Order> findUnfinishedOrders(Integer id) {
        return orderMapper.findUnfinishedOrders(id);
    }


}
