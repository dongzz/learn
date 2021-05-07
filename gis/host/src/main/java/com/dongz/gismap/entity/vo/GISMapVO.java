package com.dongz.gismap.entity.vo;

import com.vividsolutions.jts.geom.Geometry;

import java.io.Serializable;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/5/7 11:23 上午
 */
public class GISMapVO implements Serializable {
    private Integer id;
    private String nameCh;
    private Geometry geom;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameCh() {
        return nameCh;
    }

    public void setNameCh(String nameCh) {
        this.nameCh = nameCh;
    }

    public Geometry getGeom() {
        return geom;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }
}
