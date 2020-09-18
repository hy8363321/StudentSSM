package com.demo.mapper;

import com.demo.pojo.Student;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface StudentMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    Student selectByPrimaryKey(Long id);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);

    Student checkUser(@Param("studentNumber") Long userNumber, @Param("studentPassword") String password);

    int importStudentInfo( List<Student> students);

}