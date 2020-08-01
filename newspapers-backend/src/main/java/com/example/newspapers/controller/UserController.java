package com.example.newspapers.controller;

import com.example.newspapers.entity.Address;
import com.example.newspapers.entity.Submit;
import com.example.newspapers.entity.User;
import com.example.newspapers.service.AddressService;
import com.example.newspapers.service.NewspaperService;
import com.example.newspapers.service.SubmitService;
import com.example.newspapers.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/UserController")
public class UserController {
    @Resource
    NewspaperService newspaperService;
    @Resource
    UserService userService;
    @Resource
    AddressService addressService;
    @Resource
    SubmitService submitService;
    @RequestMapping("/newspaperList")
    public Map<String, Object> newspaperList(String condition, Integer page, Integer pageCount) {
        return newspaperService.newspaperList2(condition, page, pageCount);
    }

    @PostMapping("/updateProfile")
    public String updateProfile(User user) {
        return userService.updateProfile(user);
    }

    @GetMapping("/addressList")
    public List<Address> addressList(String username) {
        return addressService.addressList(username);
    }

    @PostMapping("/addAddress")
    public String addAddress(Address address) {
        return addressService.addAddress(address);
    }

    @PostMapping("/updateAddress")
    public String updateAddress(Address address) {
        return addressService.updateAddress(address);
    }

    @PostMapping("/deleteAddress")
    public String deleteAddress(Integer id) {
        return addressService.deleteAddress(id);
    }

    @GetMapping("/submitList")
    public List<Submit> submitList(String username) {
        return submitService.submitList(username);
    }

    @PostMapping("/submit")
    public String submit(Submit submit) {
        submitService.submit(submit);
        return "订阅成功";
    }
}
