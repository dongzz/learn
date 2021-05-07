package com.dongz.gismap.controller;

import com.dongz.gismap.entity.Map;
import com.dongz.gismap.service.MapService;
import com.dongz.gismap.util.Res;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

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

    @GetMapping("/list")
    public Res list() {
        List<Map> list = mapService.list();
        return Res.success(list);
    }
}
