package com.example.newspapers.dao;

import com.example.newspapers.entity.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

public interface UserDao {
    @Select("select * from user where username = #{username} and password = #{password}")
    @Results({
            @Result(property = "username", column = "username"),
            @Result(property = "password", column = "password"),
            @Result(property = "name", column = "name"),
            @Result(property = "address", column = "address"),
            @Result(property = "balance", column = "balance"),
            @Result(property = "phone", column = "phone")
    })
    User login(String username, String password);

    @Update("update user set name = #{name}, address = #{address}, phone = #{phone} where username = #{username}")
    void updateProfile(User user);
}
