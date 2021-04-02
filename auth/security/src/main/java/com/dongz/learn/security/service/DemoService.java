package com.dongz.learn.security.service;

import com.dongz.learn.security.entity.DemoEntity;

import java.util.List;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/3/17 10:40 下午
 */
public interface DemoService {

    List<DemoEntity> getUser();

}
