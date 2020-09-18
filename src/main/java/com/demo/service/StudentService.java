package com.demo.service;

import com.demo.pojo.Student;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface StudentService {
    int deleteByPrimaryKey(Long id);

    int insert(Student record);

    Student selectByPrimaryKey(Long id);

    List<Student> selectAll();

    int updateByPrimaryKey(Student record);

    Student checkUser(Long userNumber, String password);

    int importStudentInfo(List<Student> students);

}
