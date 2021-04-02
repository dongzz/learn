package com.dongz.learn.security.mapper;

import com.dongz.learn.security.entity.DemoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/3/17 10:42 下午
 */
@Component
public interface DemoMapper {

    List<DemoEntity> getUser();

}

