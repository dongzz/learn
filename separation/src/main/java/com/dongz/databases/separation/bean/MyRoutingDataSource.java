package com.dongz.databases.separation.bean;

import com.dongz.databases.separation.holder.DBContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.lang.Nullable;

/**
 * @author dz <895180729@qq.com>
 * @Description 获取路由key
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/2/19 9:43 下午
 */
public class MyRoutingDataSource extends AbstractRoutingDataSource {

    @Nullable
    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.get();
    }
}
