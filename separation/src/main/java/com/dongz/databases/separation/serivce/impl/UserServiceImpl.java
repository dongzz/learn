package com.dongz.databases.separation.serivce.impl;

import com.dongz.databases.separation.annotation.Master;
import com.dongz.databases.separation.mapper.UserMapper;
import com.dongz.databases.separation.entity.User;
import com.dongz.databases.separation.serivce.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/2/19 10:41 下午
 */
@Service(value = "userServiceImpl")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User getUserByName(String name) {
        return userMapper.getByName(name);
    }

    @Override
    @Master
    public int addUser(String id, String name) {
        return userMapper.insert(id, name);
    }
}
