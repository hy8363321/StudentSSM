package com.demo.service;

import com.demo.pojo.Types;

import java.util.List;

public interface TypesService {
    int deleteByPrimaryKey(Long id);

    int insert(Types record);

    Types selectByPrimaryKey(Long id);

    List<Types> selectAll();

    int updateByPrimaryKey(Types record);
}
