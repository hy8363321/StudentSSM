package com.demo.service.impl;

import com.demo.mapper.TestMapper;
import com.demo.pojo.Test;
import com.demo.service.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestMapper testMapper;
    @Override
    public int deleteByPrimaryKey(Long id) {
        return 0;
    }

    @Override
    public int insert(Test record) {
        return 0;
    }

    @Override
    public Test selectByPrimaryKey(Long id) {
        return null;
    }

    @Override
    public List<Test> selectAll() {
        return null;
    }

    @Override
    public int updateByPrimaryKey(Test record) {
        return 0;
    }

    @Override
    public int importTest(List<Test> list) {
        return testMapper.importTest(list);
    }
}
