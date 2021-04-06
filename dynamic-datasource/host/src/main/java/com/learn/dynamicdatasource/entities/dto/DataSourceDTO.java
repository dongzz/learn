package com.learn.dynamicdatasource.entities.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/4/2 3:47 下午
 */
@Data
public class DataSourceDTO {

    @NotBlank
    @ApiModelProperty(value = "连接池名称", example = "test")
    private String name;

    @NotBlank
    @ApiModelProperty(value = "JDBC driver", example = "com.mysql.cj.jdbc.Driver")
    private String driverClassName;

    @NotBlank
    @ApiModelProperty(value = "JDBC url 地址", example = "jdbc:mysql://localhost:3306/dynamic")
    private String url;

    @NotBlank
    @ApiModelProperty(value = "JDBC 用户名", example = "root")
    private String username;

    @ApiModelProperty(value = "密码", example = "123456")
    private String password;
}