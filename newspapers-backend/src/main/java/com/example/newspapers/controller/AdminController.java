package com.example.newspapers.controller;

import com.example.newspapers.entity.Admin;
import com.example.newspapers.entity.Newspaper;
import com.example.newspapers.entity.Submit;
import com.example.newspapers.service.AdminService;
import com.example.newspapers.service.NewspaperService;
import com.example.newspapers.service.SubmitService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/AdminController")
public class AdminController {
    @Resource
    NewspaperService newspaperService;
    @Resource
    AdminService adminService;
    @Resource
    SubmitService submitService;

    @PostMapping("/addNewspaper")
    public String addNewspaper(Newspaper newspaper, MultipartFile image) throws IOException {
        return newspaperService.addNewspaper(newspaper, image);
    }

    @RequestMapping("/newspaperList")
    public Map<String, Object> newspaperList(String adminUsername, String condition, Integer page, Integer pageCount) {
        return newspaperService.newspaperList(adminUsername, condition, page, pageCount);
    }

    @PostMapping("/updateNewspaper")
    public String updateNewspaper(Newspaper newspaper, MultipartFile image) throws IOException {
        return newspaperService.updateNewspaper(newspaper, image);
    }

    @PostMapping("/deleteNewspaper")
    public String deleteNewspaper(Integer id) {
        return newspaperService.deleteNewspaper(id);
    }

    @PostMapping("/updateProfile")
    public String updateProfile(Admin admin) {
        return adminService.updateProfile(admin);
    }

    @GetMapping("/submitList")
    public List<Submit> submitList(String adminUsername) {
        return submitService.submitListByAdminUsername(adminUsername);
    }

    @PostMapping("/manage")
    public void manage(Integer newspaperId, String username, String submitTimeStr) {
        submitService.manage(newspaperId, username, submitTimeStr);
    }
}
