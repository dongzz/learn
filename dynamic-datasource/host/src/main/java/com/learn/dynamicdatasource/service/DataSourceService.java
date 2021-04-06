package com.learn.dynamicdatasource.service;

import com.learn.dynamicdatasource.entities.DynamicDataSource;
import com.baomidou.mybatisplus.extension.service.IService;
import com.learn.dynamicdatasource.entities.dto.DataSourceDTO;

/**
 *
 */
public interface DataSourceService extends IService<DynamicDataSource> {

    boolean save(DataSourceDTO dto);
}
