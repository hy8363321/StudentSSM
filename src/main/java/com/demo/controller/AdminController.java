package com.demo.controller;

import com.alibaba.fastjson.JSONObject;
import com.demo.pojo.Admin;
import com.demo.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @PostMapping("/checkAdmin/{username}/{password}")
    public Admin checkAdmin(@PathVariable String username, @PathVariable String password){
        return null;
    }

    @PostMapping("/addAdmin")
    public void addAdmin(@RequestBody Admin admin){
        adminService.insert(admin);
    }

    @GetMapping("/getAdmin")
    public String getAdmin(){
        String admin = JSONObject.toJSONString(adminService.selectAll());
        return admin;
    }

    @PostMapping("/updateAdminById")
    public int updateAdminById(Admin admin){
        return adminService.updateByPrimaryKey(admin);
    }

    @PostMapping("/deleteAdminById")
    public int deleteAdminById(Long id){
        return adminService.deleteByPrimaryKey(id);
    }
}
