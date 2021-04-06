package com.learn.dynamicdatasource.datasource;

import com.baomidou.dynamic.datasource.provider.AbstractJdbcDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.learn.dynamicdatasource.config.DynamicDataSourceConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/4/6 2:29 下午
 */
@Component
public class MasterDynamicDataSource {

    @Resource
    DynamicDataSourceConfig dynamicDataSourceConfig;

    @Bean(name = "dynamicDataSourceProvider")
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        return new AbstractJdbcDataSourceProvider(dynamicDataSourceConfig.getDriverClassName(), dynamicDataSourceConfig.getUrl(), dynamicDataSourceConfig.getUsername(), dynamicDataSourceConfig.getPassword()) {
            @Override
            protected Map<String, DataSourceProperty> executeStmt(Statement statement) throws SQLException {
                ResultSet rs = statement.executeQuery(String.format("select * from %s", dynamicDataSourceConfig.getTableName()));
                Map<String, DataSourceProperty> map = new HashMap<>();

                while (rs.next()) {
                    String name = rs.getString("name");
                    String username = rs.getString("username");
                    String password = rs.getString("password");
                    String url = rs.getString("url");
                    String driver = rs.getString("driver");
                    DataSourceProperty property = new DataSourceProperty();
                    property.setUsername(username);
                    property.setPassword(password);
                    property.setUrl(url);
                    property.setDriverClassName(driver);
                    map.put("my_" + name, property);
                }
                // 添加当前数据源
                DataSourceProperty property = new DataSourceProperty();
                property.setUsername(dynamicDataSourceConfig.getUsername());
                property.setPassword(dynamicDataSourceConfig.getPassword());
                property.setUrl(dynamicDataSourceConfig.getUrl());
                property.setDriverClassName(dynamicDataSourceConfig.getDriverClassName());
                map.put("master", property);

                return map;
            }
        };
    }
}
