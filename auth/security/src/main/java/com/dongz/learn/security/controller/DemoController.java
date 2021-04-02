package com.dongz.learn.security.controller;

import com.dongz.learn.security.entity.DemoEntity;
import com.dongz.learn.security.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/3/17 10:39 下午
 */

@RestController
@RequestMapping("/test")
public class DemoController {

    @Autowired
    private DemoService orderService;

    @GetMapping("/getUser")
    public List<DemoEntity> getUser() {
        List<DemoEntity> result = orderService.getUser();
        return result;
    }
}
