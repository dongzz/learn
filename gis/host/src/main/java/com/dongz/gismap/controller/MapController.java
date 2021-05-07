package com.dongz.gismap.controller;

import com.dongz.gismap.entity.GisMap;
import com.dongz.gismap.entity.vo.GISMapVO;
import com.dongz.gismap.service.MapService;
import com.dongz.gismap.util.Res;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/5/7 10:41 上午
 */
@RestController
@RequestMapping("/map")
public class MapController {

    @Resource
    MapService mapService;

    @GetMapping(value = "/list")
    public Res list() {
        List<GISMapVO> list = mapService.listVO();
        List<String> collect = list.stream().map(o -> o.getGeom().getCoordinates()[0].x + "," + o.getGeom().getCoordinates()[0].y).collect(Collectors.toList());
        return Res.success(collect);
    }
}
