package com.learn.dynamicdatasource.controller;

import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.provider.DynamicDataSourceProvider;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.learn.dynamicdatasource.entities.DynamicDataSource;
import com.learn.dynamicdatasource.entities.dto.DataSourceDTO;
import com.learn.dynamicdatasource.service.DataSourceService;
import com.learn.dynamicdatasource.tools.Res;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.util.Assert;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.Set;

/**
 * @author dz <895180729@qq.com>
 * @Description 数据源管理
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/4/2 3:39 下午
 */
@Api(tags = "数据源管理")
@RestController
@RequestMapping("/datasource")
public class DataSourceController {

    @Resource(name = "dynamicDataSourceProvider")
    DynamicDataSourceProvider dynamicDataSourceProvider;

    @Resource
    DefaultDataSourceCreator dataSourceCreator;

    @Resource(name = "dataSourceServiceImpl")
    DataSourceService dataSourceService;

    @GetMapping(value = "/list")
    @ApiOperation("获取当前所有数据源")
    public Res list() {
        List<DynamicDataSource> list = dataSourceService.list();
        return Res.success(list);
    }

    @PostMapping("/add")
    @ApiOperation("通用添加数据源（推荐）")
    public Set<String> add(@Validated @RequestBody DataSourceDTO dto) {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        BeanUtils.copyProperties(dto, dataSourceProperty);
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        Assert.isTrue(dataSourceService.save(dto), "数据源存储失败");
        dynamicDataSourceProvider.loadDataSources().put(dto.getPollName(), dataSource);
        return dynamicDataSourceProvider.loadDataSources().keySet();
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除数据源")
    public String remove(String name) {
        dynamicDataSourceProvider.loadDataSources().remove(name);
        return "删除成功";
    }

    @PostMapping("/select")
    @ApiOperation("动态查询")
    public DataSource select(String source, String sql) throws SQLException {
        DataSource dataSource = dynamicDataSourceProvider.loadDataSources().get(source);
        if (dataSource == null) {
            return null;
        }
        Connection connection = dataSource.getConnection();
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
        return null;
    }
}
