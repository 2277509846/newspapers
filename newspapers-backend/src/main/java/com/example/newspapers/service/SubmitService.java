package com.example.newspapers.service;

import com.example.newspapers.entity.Submit;

import java.util.List;

public interface SubmitService {
    List<Submit> submitList(String username);
    List<Submit> submitListByAdminUsername(String adminUsername);
    void manage(Integer newspaperId, String username, String submitTimeStr);
    void submit(Submit submit);
}
