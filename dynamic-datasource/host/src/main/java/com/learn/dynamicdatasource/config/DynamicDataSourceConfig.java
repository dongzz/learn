package com.learn.dynamicdatasource.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/4/6 2:19 下午
 */
@Data
@Component
@ConfigurationProperties(prefix = "spring.datasource.dynamic.datasource.master")
public class DynamicDataSourceConfig {
    private String url;
    private String username;
    private String password;
    private String driverClassName;
    private String tableName;
}
