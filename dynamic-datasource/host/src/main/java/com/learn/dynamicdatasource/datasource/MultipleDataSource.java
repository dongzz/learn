package com.learn.dynamicdatasource.datasource;

import com.baomidou.dynamic.datasource.ds.AbstractRoutingDataSource;
import com.learn.dynamicdatasource.handler.DataSourceContextHolder;

import javax.sql.DataSource;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/4/6 5:02 下午
 */
public class MultipleDataSource extends AbstractRoutingDataSource {
    @Override
    protected DataSource determineDataSource() {
        return DataSourceContextHolder.getDataSource();
    }
}
