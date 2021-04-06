package com.learn.dynamicdatasource.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.learn.dynamicdatasource.entities.DynamicDataSource;
import com.learn.dynamicdatasource.entities.dto.DataSourceDTO;
import com.learn.dynamicdatasource.service.DataSourceService;
import com.learn.dynamicdatasource.mapper.DataSourceMapper;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 */
@Service("dataSourceServiceImpl")
public class DataSourceServiceImpl extends ServiceImpl<DataSourceMapper, DynamicDataSource>
implements DataSourceService{

    @Resource
    DataSourceMapper dataSourceMapper;

    @Override
    public boolean save(DataSourceDTO dto) {
        Assert.isTrue(checkName(dto.getPollName()), "数据源名称重复");
        Assert.isTrue(checkDataSource(dto), "数据库连接失败");
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setName(dto.getPollName());
        dataSource.setDriver(dto.getDriverClassName());
        dataSource.setUrl(dto.getUrl());
        dataSource.setUsername(dto.getUsername());
        dataSource.setPassword(dto.getPassword());
        return this.save(dataSource);
    }

    private boolean checkDataSource(DataSourceDTO dto) {
        try {
            DriverManager.getConnection(dto.getUrl(), dto.getPollName(), dto.getPassword());
        } catch (SQLException e) {
            log.error(String.format("数据源配置 %s , 获取链接失败", dto.getUrl()), e);
            return false;
        }
        return true;
    }

    private boolean checkName(String name) {
        QueryWrapper<DynamicDataSource> qw = new QueryWrapper<>();
        qw.eq("name", name);
        DynamicDataSource one = this.getOne(qw);
        return one == null;
    }
}




