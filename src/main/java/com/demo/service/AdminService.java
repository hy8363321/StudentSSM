package com.demo.service;

import com.demo.pojo.Admin;

import java.util.List;

public interface AdminService {
    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    Admin selectByPrimaryKey(Long id);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);
}
