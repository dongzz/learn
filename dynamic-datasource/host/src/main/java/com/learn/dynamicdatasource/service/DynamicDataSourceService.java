package com.learn.dynamicdatasource.service;

import java.util.List;
import java.util.Map;

/**
 * @author dz <895180729@qq.com>
 * @Description 动态数据源
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/4/7 9:49 上午
 */
public interface DynamicDataSourceService {

    List<Map<String, Object>> list(String source, String sql);
}
