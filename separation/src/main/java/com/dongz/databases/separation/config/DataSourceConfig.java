package com.dongz.databases.separation.config;

import com.dongz.databases.separation.bean.MyRoutingDataSource;
import com.dongz.databases.separation.enums.DBTypeEnum;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

/**
 * @author dz <895180729@qq.com>
 * @Description 多数据源配置
 * 这里，我们配置了3个数据源，1个master，1两个slave，1个路由数据源。前3个数据源都是为了生成第3个数据源，而且后续我们只用这最后一个路由数据源。
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/2/19 9:34 下午
 */
@Configuration
public class DataSourceConfig {

    @Value("${spring.datasource.type}")
    private Class<? extends DataSource> dataSourceType;

    @Bean
    @ConfigurationProperties("spring.datasource.master")
    public DataSource masterDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean
    @ConfigurationProperties("spring.datasource.slave")
    public DataSource slaveDataSource() {
        return DataSourceBuilder.create().type(dataSourceType).build();
    }

    @Bean
    public DataSource myRoutingDataSource(@Qualifier("masterDataSource") DataSource masterDataSource,
                                          @Qualifier("slaveDataSource") DataSource slaveDataSource) {
        Map<Object, Object> targetDataSources = new HashMap<>();
        targetDataSources.put(DBTypeEnum.Master, masterDataSource);
        targetDataSources.put(DBTypeEnum.Slave, slaveDataSource);

        MyRoutingDataSource myRoutingDataSource = new MyRoutingDataSource();
        myRoutingDataSource.setDefaultTargetDataSource(masterDataSource);
        myRoutingDataSource.setTargetDataSources(targetDataSources);
        return myRoutingDataSource;
    }
}
