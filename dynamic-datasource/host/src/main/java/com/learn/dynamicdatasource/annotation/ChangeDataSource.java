package com.learn.dynamicdatasource.annotation;

import java.lang.annotation.*;

/**
 * @author dz <895180729@qq.com>
 * @Description 切换数据源
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/4/7 9:37 上午
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ChangeDataSource {

}
