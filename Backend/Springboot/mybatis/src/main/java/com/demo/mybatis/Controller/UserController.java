package com.demo.mybatis.Controller;

import com.demo.mybatis.mapper.UserMapper;
import com.demo.mybatis.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Slf4j
@RestController
public class UserController {

    private final UserMapper userMapper;
    // 使用构造函数注入
    @Autowired
    public UserController(UserMapper userMapper) {
        this.userMapper = userMapper;
        // 添加或设置其他属性
    }
    @GetMapping("/getUsers")
    public List<User> getUsers() {
        log.info("getUsers");
        return userMapper.getUsers();
    }
}
