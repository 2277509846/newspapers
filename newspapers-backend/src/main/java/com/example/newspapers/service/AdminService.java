package com.example.newspapers.service;

import com.example.newspapers.entity.Admin;

public interface AdminService {
    Admin login(String username, String password);
    String updateProfile(Admin admin);
}
