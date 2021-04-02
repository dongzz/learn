package com.dongz.learn.security.service.impl;

import com.dongz.learn.security.entity.DemoEntity;
import com.dongz.learn.security.mapper.DemoMapper;
import com.dongz.learn.security.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/3/17 10:41 下午
 */

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private DemoMapper demoMapper;

    @Override
    public List<DemoEntity> getUser() {
        return demoMapper.getUser();
    }
}