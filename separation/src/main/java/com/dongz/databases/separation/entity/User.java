package com.dongz.databases.separation.entity;

import java.io.Serializable;

/**
 * @author dz <895180729@qq.com>
 * @Description 用户表
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/2/19 10:15 下午
 */
public class User implements Serializable {
    private String id;
    private String name;

    public User(String id, String name) {
        this.id = id;
        this.name = name;
    }
}
