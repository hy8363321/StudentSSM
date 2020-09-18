package com.demo.mapper;

import com.demo.pojo.Admin;
import org.springframework.stereotype.Component;

import java.util.List;

public interface AdminMapper {

    int deleteByPrimaryKey(Long id);

    int insert(Admin record);

    Admin selectByPrimaryKey(Long id);

    List<Admin> selectAll();

    int updateByPrimaryKey(Admin record);
}