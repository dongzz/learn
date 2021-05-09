package com.dongz.gismap.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.dongz.gismap.entity.GisMap;
import com.dongz.gismap.service.MapService;
import com.dongz.gismap.util.Res;
import org.geotools.geojson.geom.GeometryJSON;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.StringWriter;
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

    @GetMapping("/geometry")
    @CrossOrigin
    public Res getPoint(@RequestParam("category") String category, @RequestParam("start") Integer start, @RequestParam("end") Integer end){
        List<GisMap> list = mapService.getDynastyGeom(category,start,end);
        GeometryJSON geometryJSON=new GeometryJSON();
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
            StringWriter writer = new StringWriter();
            try {
                geometryJSON.write(g.getGeom(),writer);
                geom.put("geometry", JSONObject.parse(writer.toString()));
            } catch (IOException e) {
                e.printStackTrace();
                geom.put("geometry", null);
            }
            return geom;
        }).collect(Collectors.toCollection(JSONArray::new));
        return Res.success(collect);
    }
}
