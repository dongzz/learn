package com.dongz.databases.separation.controllers;

import com.dongz.databases.separation.entity.User;
import com.dongz.databases.separation.serivce.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/2/19 10:54 下午
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource(name = "userServiceImpl")
    UserService userService;

    @PostMapping("/add")
    public int add() {
        return userService.addUser("1", "dongz");
    }
    @GetMapping("/get")
    public User get() {
        return userService.getUserByName("dongz");
    }
}
