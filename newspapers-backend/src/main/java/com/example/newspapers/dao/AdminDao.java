package com.example.newspapers.dao;

import com.example.newspapers.entity.Admin;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface AdminDao {
    @Select("select * from admin where username = #{username} and password = #{password}")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "name", column = "name"),
            @Result(property = "address", column = "address"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "phone", column = "phone")
    })
    Admin login(String username, String password);

    @Update("update admin set name = #{name}, address = #{address}, phone = #{phone} where username = #{username}")
    void updateProfile(Admin admin);
}
