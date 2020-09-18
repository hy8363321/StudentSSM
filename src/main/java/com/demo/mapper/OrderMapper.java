package com.demo.mapper;

import com.demo.pojo.Order;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Order record);

    Order selectByPrimaryKey(Long id);

    List<Order> selectAll();

    int updateByPrimaryKey(Order record);
}