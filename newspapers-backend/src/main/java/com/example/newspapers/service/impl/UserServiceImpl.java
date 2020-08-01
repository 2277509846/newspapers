package com.example.newspapers.service.impl;

import com.example.newspapers.dao.UserDao;
import com.example.newspapers.entity.User;
import com.example.newspapers.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;
    @Override
    public User login(String username, String password) {
        return userDao.login(username, password);
    }

    @Override
    public String updateProfile(User user) {
        userDao.updateProfile(user);
        return "修改成功";
    }

}
