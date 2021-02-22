package com.dongz.databases.separation.holder;

import com.dongz.databases.separation.enums.DBTypeEnum;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dz <895180729@qq.com>
 * @Description 通过ThreadLocal将数据源设置到每个线程上下文中
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/2/19 9:54 下午
 */
public class DBContextHolder {

    private static final ThreadLocal<DBTypeEnum> contextHolder = new ThreadLocal<>();

    private static final AtomicInteger counter = new AtomicInteger();

    public static void set(DBTypeEnum dbTypeEnum) {
        contextHolder.set(dbTypeEnum);
    }

    public static DBTypeEnum get() {
        return contextHolder.get();
    }

    public static void master() {
        set(DBTypeEnum.Master);
        System.out.println("切换到master");
    }

    public static void slave() {
        // 多个从数据库需要轮询
        /*int index = counter.getAndIncrement() %2;
        if (counter.get() > 9999) {
            counter.set(-1);
        }*/

        set(DBTypeEnum.Slave);
        System.out.println("切换到slave");
    }
}
