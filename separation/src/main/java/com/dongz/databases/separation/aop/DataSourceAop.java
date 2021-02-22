package com.dongz.databases.separation.aop;

import com.dongz.databases.separation.holder.DBContextHolder;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author dz <895180729@qq.com>
 * @Description 默认情况下，所有的查询都走从库，插入/修改/删除走主库。我们通过方法名来区分操作类型（CRUD）
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/2/19 10:10 下午
 */
@Aspect
@Component
public class DataSourceAop {

    @Pointcut("!@annotation(com.dongz.databases.separation.annotation.Master) " +
            "&& (execution(* com.dongz.databases.separation.serivce..*.select*(..)) " +
            "|| execution(* com.dongz.databases.separation.serivce..*.get*(..)))")
    public void readPointcut() {

    }

    @Pointcut("@annotation(com.dongz.databases.separation.annotation.Master) " +
            "|| execution(* com.dongz.databases.separation.serivce..*.insert*(..)) " +
            "|| execution(* com.dongz.databases.separation.serivce..*.add*(..))")
    public void writePointcut() {

    }

    @Before("readPointcut()")
    public void read() {
        DBContextHolder.slave();
    }

    @Before("writePointcut()")
    public void write() {
        DBContextHolder.master();
    }
}
