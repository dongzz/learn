package com.learn.dynamicdatasource.handler;

import javax.sql.DataSource;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/4/6 4:58 下午
 */
public class DataSourceContextHolder {
    private static final ThreadLocal<DataSource> contextHolder = new InheritableThreadLocal<>();

    /**
     *  设置数据源
     * @param db
     */
    public static void setDataSource(DataSource db){
        contextHolder.set(db);
    }

    /**
     * 取得当前数据源
     * @return
     */
    public static DataSource getDataSource(){
        return contextHolder.get();
    }

    /**
     * 清除上下文数据
     */
    public static void clear(){
        contextHolder.remove();
    }
}
