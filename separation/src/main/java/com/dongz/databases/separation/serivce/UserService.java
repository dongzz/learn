package com.dongz.databases.separation.serivce;

import com.dongz.databases.separation.entity.User;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/2/19 10:40 下午
 */
public interface UserService {
    User getUserByName(String name);

    int addUser(String id, String name);
}
