package com.learn.dynamicdatasource.controller;

import com.baomidou.dynamic.datasource.creator.DefaultDataSourceCreator;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.learn.dynamicdatasource.annotation.ChangeDataSource;
import com.learn.dynamicdatasource.datasource.IDynamicDataSourceProvider;
import com.learn.dynamicdatasource.entities.DynamicDataSource;
import com.learn.dynamicdatasource.entities.dto.DataSourceDTO;
import com.learn.dynamicdatasource.service.DataSourceService;
import com.learn.dynamicdatasource.service.DynamicDataSourceService;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

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

    @Resource
    IDynamicDataSourceProvider dynamicDataSource;

    @Resource
    DefaultDataSourceCreator dataSourceCreator;

    @Resource(name = "dataSourceServiceImpl")
    DataSourceService dataSourceService;

    @Resource(name = "dynamicDataSourceServiceImpl")
    DynamicDataSourceService dynamicDataSourceService;

    @GetMapping(value = "/list")
    @ApiOperation("获取当前所有数据源")
    public Res list() {
        List<DynamicDataSource> list = dataSourceService.list();
        return Res.success(list);
    }

    @PostMapping("/add")
    @ApiOperation("通用添加数据源")
    public Res add(@Validated @RequestBody DataSourceDTO dto) {
        DataSourceProperty dataSourceProperty = new DataSourceProperty();
        BeanUtils.copyProperties(dto, dataSourceProperty);
        DataSource dataSource = dataSourceCreator.createDataSource(dataSourceProperty);
        Assert.isTrue(dataSourceService.save(dto), "数据源存储失败");
        dynamicDataSource.put(dto.getName(), dataSource);
        return Res.success("新增数据源成功");
    }

    @DeleteMapping("/delete")
    @ApiOperation("删除数据源")
    public Res remove(String name) {
        dataSourceService.remove(name);
        dynamicDataSource.remove(name);
        return Res.success("删除成功");
    }

    @ChangeDataSource
    @PostMapping("/select")
    @ApiOperation("动态查询")
    public Res select(String source, String sql) {
        DataSource dataSource = dynamicDataSource.get(source);
        if (dataSource == null) {
            return null;
        }
        List<Map<String,Object>> list = dynamicDataSourceService.list(sql);
        return Res.success(list);
    }
}
