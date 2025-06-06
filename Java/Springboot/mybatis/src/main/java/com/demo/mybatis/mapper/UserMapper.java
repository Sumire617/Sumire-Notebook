package com.demo.mybatis.mapper;

import com.demo.mybatis.model.User;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM mybatis_users")
    @Results({
            @Result(property = "id", column = "id"),
            @Result(property = "name", column = "username"),
            @Result(property = "age", column = "age"),
            @Result(property = "email", column = "email")
    })
    List<User> getUsers();
}
