package com.learn.dynamicdatasource.service;

import com.learn.dynamicdatasource.entities.DataSource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.dynamicdatasource.entities.dto.DataSourceDTO;

/**
 *
 */
public interface DataSourceService extends IService<DataSource> {

    boolean save(DataSourceDTO dto);
}
