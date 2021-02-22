package com.dongz.databases.separation.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * @author dz <895180729@qq.com>
 * @Description mybatis 配置
 * 由于Spring容器中现在有3个数据源，所以我们需要为事务管理器和MyBatis手动指定一个明确的数据源
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/2/19 9:46 下午
 */
@EnableTransactionManagement
@Configuration
public class MyBatisConfig {
    @Resource(name = "myRoutingDataSource")
    private DataSource myRoutingDataSource;

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(myRoutingDataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.dongz.databases.separation.mapper");
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mappers/*Mapper.xml"));
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事务管理
     * @return
     */
    @Bean
    public PlatformTransactionManager platformTransactionManager() {
        return new DataSourceTransactionManager(myRoutingDataSource);
    }
}
