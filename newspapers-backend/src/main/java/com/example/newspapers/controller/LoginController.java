package com.example.newspapers.controller;

import com.example.newspapers.entity.Admin;
import com.example.newspapers.entity.User;
import com.example.newspapers.service.AdminService;
import com.example.newspapers.service.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/LoginController")
public class LoginController {
    @Resource
    UserService userService;
    @Resource
    AdminService adminService;
    @PostMapping("/userLogin")
    public User userLogin(String username, String password) {
        User user = userService.login(username, password);
        if (user != null) user.setPassword(null);
        return user;
    }
    @PostMapping("/adminLogin")
    public Admin adminLogin(String username, String password) {
        Admin admin = adminService.login(username, password);
        if (admin != null) admin.setPassword(null);
        return admin;
    }
}
