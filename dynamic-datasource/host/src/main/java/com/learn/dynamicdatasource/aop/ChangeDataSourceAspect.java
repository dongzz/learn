package com.learn.dynamicdatasource.aop;

import com.learn.dynamicdatasource.annotation.ChangeDataSource;
import com.learn.dynamicdatasource.datasource.IDynamicDataSourceProvider;
import com.learn.dynamicdatasource.handler.DataSourceContextHolder;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.lang.reflect.Method;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/4/7 9:38 上午
 */
@Aspect
@Component
public class ChangeDataSourceAspect {
    @Resource
    IDynamicDataSourceProvider dynamicDataSource;

    @Pointcut("@annotation(com.learn.dynamicdatasource.annotation.ChangeDataSource)")
    public void dataSourcePointCut() {

    }

    @Around("dataSourcePointCut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        Method method = signature.getMethod();

        ChangeDataSource dataSource = method.getAnnotation(ChangeDataSource.class);
        if(dataSource != null){
            String source = (String) point.getArgs()[0];
            Assert.isTrue(dynamicDataSource.containsKey(source), "数据源不存在");
            DataSourceContextHolder.setDataSource(dynamicDataSource.get(source));
        }

        try {
            return point.proceed();
        } finally {
            DataSourceContextHolder.setDataSource(dynamicDataSource.get("master"));
        }
    }
}
