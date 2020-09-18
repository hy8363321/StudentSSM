package com.demo.mapper;

import com.demo.pojo.Types;
import java.util.List;

public interface TypesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Types record);

    Types selectByPrimaryKey(Long id);

    List<Types> selectAll();

    int updateByPrimaryKey(Types record);
}