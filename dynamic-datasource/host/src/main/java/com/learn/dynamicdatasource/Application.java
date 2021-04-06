package com.learn.dynamicdatasource;

import com.baomidou.dynamic.datasource.provider.AbstractJdbcDataSourceProvider;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

@SpringBootApplication
@EnableSwagger2
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Bean(name = "dynamicDataSourceProvider")
    public DynamicDataSourceProvider dynamicDataSourceProvider() {
        return new AbstractJdbcDataSourceProvider("com.mysql.cj.jdbc.Driver", "jdbc:mysql://localhost:3306/dynamic", "root", "123456") {
            @Override
            protected Map<String, DataSourceProperty> executeStmt(Statement statement) throws SQLException {
                ResultSet rs = statement.executeQuery("select * from data_source");
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
                    map.put(name, property);
                }
                return map;
            }
        };
    }
}
