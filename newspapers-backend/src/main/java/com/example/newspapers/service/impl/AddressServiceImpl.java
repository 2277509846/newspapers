package com.example.newspapers.service.impl;

import com.example.newspapers.dao.AddressDao;
import com.example.newspapers.entity.Address;
import com.example.newspapers.service.AddressService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    @Resource
    AddressDao addressDao;

    @Override
    public List<Address> addressList(String username) {
        return addressDao.addressList(username);
    }

    @Override
    public String addAddress(Address address) {
        addressDao.addAddress(address);
        return "添加成功";
    }

    @Override
    public String updateAddress(Address address) {
        addressDao.updateAddress(address);
        return "修改成功";
    }

    @Override
    public String deleteAddress(Integer id) {
        addressDao.deleteAddress(id);
        return "删除成功";
    }
}
