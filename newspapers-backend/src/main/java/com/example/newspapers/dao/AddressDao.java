package com.example.newspapers.dao;

import com.example.newspapers.entity.Address;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface AddressDao {

    @Select("select * from address where username = #{username}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "consignee", column = "consignee"),
            @Result(property = "address", column = "address"),
            @Result(property = "phone", column = "phone"),
            @Result(property = "username", column = "username")
    })
    List<Address> addressList(String username);

    @Transactional
    @Insert("insert into address(consignee, address, phone, username) values(#{consignee}, #{address}, #{phone}, #{username})")
    void addAddress(Address address);

    @Transactional
    @Update("update address set consignee = #{consignee}, address = #{address}, phone = #{phone} where id = #{id}")
    void updateAddress(Address address);

    @Transactional
    @Delete("delete from address where id = #{id}")
    void deleteAddress(Integer id);

    @Select("select address from address where id = #{id}")
    String findAddressNameById(Integer id);
}
