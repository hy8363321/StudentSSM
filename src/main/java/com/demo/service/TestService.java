package com.demo.service;

import com.demo.pojo.Test;

import java.util.List;

public interface TestService {
    int deleteByPrimaryKey(Long id);

    int insert(Test record);

    Test selectByPrimaryKey(Long id);

    List<Test> selectAll();

    int updateByPrimaryKey(Test record);

    int importTest(List<Test> list);
}
