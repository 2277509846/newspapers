package com.example.newspapers.service.impl;

import com.example.newspapers.dao.AdminDao;
import com.example.newspapers.entity.Admin;
import com.example.newspapers.service.AdminService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class AdminServiceImpl implements AdminService {
    @Resource
    AdminDao adminDao;
    @Override
    public Admin login(String username, String password) {
        return adminDao.login(username, password);
    }

    @Override
    public String updateProfile(Admin admin) {
        adminDao.updateProfile(admin);
        return "修改成功";
    }
}
