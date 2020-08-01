package com.example.newspapers.service;

import com.example.newspapers.entity.Address;

import java.util.List;

public interface AddressService {
    List<Address> addressList(String username);
    String addAddress(Address address);
    String updateAddress(Address address);
    String deleteAddress(Integer id);
}
