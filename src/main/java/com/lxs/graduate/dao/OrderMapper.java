package com.lxs.graduate.dao;

import com.lxs.graduate.entity.Order;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface OrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<Order> findAllOrders();

    List<Order> findOrderByBuyId(Integer id);

    List<Order> findOrderBySellId(Integer id);
}