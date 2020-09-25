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
    @ResponseBody
    public Admin checkAdmin(@PathVariable String username, @PathVariable String password) {
        return null;
    }

    @PostMapping("/addAdmin")
    @ResponseBody
    public void addAdmin(@RequestBody Admin admin) {
        adminService.insert(admin);
    }

    @GetMapping("/getAdmin")
    @ResponseBody
    public String getAdmin() {
        return JSONObject.toJSONString(adminService.selectAll());
    }

    @PostMapping("/updateAdminById")
    @ResponseBody
    public int updateAdminById(Admin admin) {
        return adminService.updateByPrimaryKey(admin);
    }

    @PostMapping("/deleteAdminById")
    @ResponseBody
    public int deleteAdminById(Long id) {
        return adminService.deleteByPrimaryKey(id);
    }
}
