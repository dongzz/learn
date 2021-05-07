package com.dongz.gismap.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.dongz.gismap.entity.GisMap;
import com.dongz.gismap.entity.vo.GISMapVO;

import java.util.List;

/**
*
*/
public interface MapService extends IService<GisMap> {

    List<GISMapVO> listVO();
}
