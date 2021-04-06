package com.learn.dynamicdatasource.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.learn.dynamicdatasource.entities.DynamicDataSource;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Entity com.learn.dynamicdatasource.entities.DataSource
 */
@Mapper
public interface DataSourceMapper extends BaseMapper<DynamicDataSource> {

}




