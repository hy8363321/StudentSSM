package com.demo.service;

import com.demo.pojo.Order;

import java.util.List;

public interface OrderSerivce {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    Order selectByPrimaryKey(Long id);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);
}
