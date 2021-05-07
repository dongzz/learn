package com.dongz.gismap.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dongz.gismap.entity.GisMap;
import com.dongz.gismap.entity.vo.GISMapVO;
import com.dongz.gismap.service.MapService;
import com.dongz.gismap.util.Res;
import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Arrays;
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
        List<GisMap> list = mapService.list();
        JSONArray collect = list.stream().map(g -> {
            JSONObject geom = new JSONObject();
            geom.put("id", g.getId());
            geom.put("namepy", g.getNamePy());
            geom.put("namech", g.getNameCh());
            geom.put("nameft", g.getNameFt());
            geom.put("presloc", g.getPresLoc());
            geom.put("typepy", g.getTypePy());
            geom.put("typech", g.getTypeCh());
            geom.put("levrank", g.getLevRank());
            geom.put("begyr", g.getBegYr());
            geom.put("begrule", g.getBegRule());
            geom.put("endyr", g.getEndYr());
            geom.put("endrule", g.getEndRule());
            geom.put("geosrc", g.getGeoSrc());
            geom.put("compiler", g.getCompiler());
            geom.put("gecomplr", g.getGecomplr());
            geom.put("checker", g.getChecker());
            geom.put("entdate", g.getEntDate());
            geom.put("begchgty", g.getBegChgTy());
            geom.put("endchgty", g.getEndChgTy());
            geom.put("geometry", geometryToJson(g.getGeom()));
            return geom;
        }).collect(Collectors.toCollection(JSONArray::new));
        return Res.success(collect);
    }

    private JSONObject geometryToJson(Geometry geometry){
        JSONObject jsonObject=new JSONObject();
        jsonObject.put("type",geometry.getGeometryType());
        JSONArray coorList = Arrays.stream(geometry.getCoordinates()).map(c -> {
            JSONObject jsonObjectCoor = new JSONObject();
            jsonObjectCoor.put("lng", c.x);
            jsonObjectCoor.put("lat", c.y);
            return jsonObjectCoor;
        }).collect(Collectors.toCollection(JSONArray::new));

        jsonObject.put("coordinates",coorList);
        return jsonObject;
    }
}
