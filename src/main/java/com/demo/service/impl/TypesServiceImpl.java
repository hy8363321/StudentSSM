package com.demo.service.impl;

import com.demo.mapper.TypesMapper;
import com.demo.pojo.Types;
import com.demo.service.TypesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypesServiceImpl implements TypesService {

    @Autowired
    private TypesMapper typesMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return typesMapper.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Types record) {
        return typesMapper.insert(record);
    }

    @Override
    public Types selectByPrimaryKey(Long id) {
        return typesMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Types> selectAll() {
        return typesMapper.selectAll();
    }

    @Override
    public int updateByPrimaryKey(Types record) {
        return typesMapper.updateByPrimaryKey(record);
    }
}
