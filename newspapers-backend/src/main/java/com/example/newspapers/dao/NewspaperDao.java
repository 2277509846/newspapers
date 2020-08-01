package com.example.newspapers.dao;

import com.example.newspapers.entity.Newspaper;
import org.apache.ibatis.annotations.*;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NewspaperDao {
    @Transactional
    @Insert("insert newspaper(name, category, price, admin_username, description, update_time, image_url)" +
            " values(#{name}, #{category}, #{price}, #{admin_username}," +
            " #{description}, #{update_time}, #{image_url})")
    void addNewspaper(Newspaper newspaper);

    @Transactional
    @Delete("update newspaper set is_delete = 1 where id = #{id}")
    void deleteNewspaper(Integer id);

    @Transactional
    @Update("update newspaper" +
            " set name = #{name}, category = #{category}, price = #{price}, " +
            "admin_username = #{admin_username}, description = #{description}, " +
            "update_time = #{update_time}, image_url = #{image_url}" +
            " where id = #{id}")
    void updateNewspaper(Newspaper newspaper);

    @Select("select * from newspaper where is_delete = 0 and id = #{id}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "category", column = "category"),
            @Result(property = "price", column = "price"),
            @Result(property = "admin_username", column = "admin_username"),
            @Result(property = "description", column = "description"),
            @Result(property = "update_time", column = "update_time"),
            @Result(property = "image_url", column = "image_url")
    })
    Newspaper findNewspaperById(Integer id);

    @Select("select * from newspaper where is_delete = 0 and admin_username = #{adminUsername} and name like #{condition} limit #{page}, #{pageCount}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "category", column = "category"),
            @Result(property = "price", column = "price"),
            @Result(property = "admin_username", column = "admin_username"),
            @Result(property = "description", column = "description"),
            @Result(property = "update_time", column = "update_time"),
            @Result(property = "image_url", column = "image_url")
    })
    List<Newspaper> newspaperList(String adminUsername, String condition, Integer page, Integer pageCount);

    @Select("select * from newspaper where is_delete = 0 and name like #{condition} limit #{page}, #{pageCount}")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "name"),
            @Result(property = "category", column = "category"),
            @Result(property = "price", column = "price"),
            @Result(property = "admin_username", column = "admin_username"),
            @Result(property = "description", column = "description"),
            @Result(property = "update_time", column = "update_time"),
            @Result(property = "image_url", column = "image_url")
    })
    List<Newspaper> newspaperList2(String condition, Integer page, Integer pageCount);

    @Select("select count(*) from newspaper where is_delete = 0 and name like #{condition}")
    Integer total(String condition);

    @Select("select count(*) from newspaper where is_delete = 0 and name like #{condition} and admin_username = #{adminUsername}")
    Integer total2(String condition, String adminUsername);
}
