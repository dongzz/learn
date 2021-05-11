package com.dongz.gismap.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dongz.gismap.entity.GisMap;
import com.dongz.gismap.entity.vo.GISMapVO;
import com.dongz.gismap.mapper.MapMapper;
import com.dongz.gismap.service.MapService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
*
*/
@Service
public class MapServiceImpl extends ServiceImpl<MapMapper, GisMap> implements MapService{

    @Resource
    MapMapper mapMapper;

    @Override
    public List<GISMapVO> listVO() {
        return mapMapper.selectVOList();
    }

    @Override
    public List<GisMap> getDynastyGeom(String category, Integer start, Integer end) {
        return this.mapMapper.getDynastyGeometry(category, start, end);
    }
}
