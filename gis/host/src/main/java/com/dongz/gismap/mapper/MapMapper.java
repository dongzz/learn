package com.dongz.gismap.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.dongz.gismap.entity.GisMap;
import com.dongz.gismap.entity.vo.GISMapVO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
* @Entity com.dongz.gismap.entity.Map
*/
@Mapper
public interface MapMapper extends BaseMapper<GisMap> {


    List<GISMapVO> selectVOList();
}
