package com.dongz.gismap.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongz.gismap.entity.GisMap;
import com.dongz.gismap.service.MapService;
import com.dongz.gismap.mapper.MapMapper;
import org.springframework.stereotype.Service;

/**
*
*/
@Service
public class MapServiceImpl extends ServiceImpl<MapMapper, GisMap> implements MapService{

}
