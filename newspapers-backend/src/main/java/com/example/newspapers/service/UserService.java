package com.example.newspapers.service;

import com.example.newspapers.entity.User;

public interface UserService {
    User login(String username, String password);
    String updateProfile(User user);
}
