package com.example.newspapers.dao;

import com.example.newspapers.entity.Submit;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

public interface SubmitDao {
    @Select("select * from submit where username = #{username}")
    @Results({
            @Result(property = "newspaperId", column = "newspaper_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "submitTime", column = "submit_time"),
            @Result(property = "fee", column = "fee"),
            @Result(property = "addressId", column = "address_id"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "count", column = "count")
    })
    List<Submit> submitList(String username);

    @Select("select * from submit where newspaper_id in (select id from newspaper where admin_username = #{adminUsername})")
    @Results({
            @Result(property = "newspaperId", column = "newspaper_id"),
            @Result(property = "username", column = "username"),
            @Result(property = "submitTime", column = "submit_time"),
            @Result(property = "fee", column = "fee"),
            @Result(property = "addressId", column = "address_id"),
            @Result(property = "startDate", column = "start_date"),
            @Result(property = "endDate", column = "end_date"),
            @Result(property = "status", column = "status"),
            @Result(property = "count", column = "count")
    })
    List<Submit> submitListByAdminUsername(String adminUsername);

    @Update("update submit set status = 1 where newspaper_id = #{newspaperId} and username = #{username} and submit_time = #{submitTimeStr}")
    void manage(Integer newspaperId, String username, String submitTimeStr);

    @Insert("insert into submit values(#{newspaperId}, #{username}, #{submitTime}, #{fee}, #{addressId}, #{startDateStr}, #{endDateStr}, b'0', #{count})")
    void submit(Submit submit);
}
