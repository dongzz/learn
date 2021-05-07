package com.dongz.gismap.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import com.dongz.gismap.handler.GeometryTypeHandler;
import com.vividsolutions.jts.geom.Geometry;

/**
 * 
 * @TableName v6_time_cnty_pts_utf_wgs84
 */
@TableName(value ="v6_time_cnty_pts_utf_wgs84", autoResultMap = true)
public class GisMap implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    @TableField(typeHandler = GeometryTypeHandler.class)
    private Geometry geom;

    /**
     * 
     */
    private String namePy;

    /**
     * 
     */
    private String nameCh;

    /**
     * 
     */
    private String nameFt;

    /**
     * 
     */
    private Double xCoor;

    /**
     * 
     */
    private Double yCoor;

    /**
     * 
     */
    private String presLoc;

    /**
     * 
     */
    private String typePy;

    /**
     * 
     */
    private String typeCh;

    /**
     * 
     */
    private String levRank;

    /**
     * 
     */
    private Long begYr;

    /**
     * 
     */
    private String begRule;

    /**
     * 
     */
    private Long endYr;

    /**
     * 
     */
    private String endRule;

    /**
     * 
     */
    private Long noteId;

    /**
     * 
     */
    private String objType;

    /**
     * 
     */
    private Long sysId;

    /**
     * 
     */
    private String geoSrc;

    /**
     * 
     */
    private String compiler;

    /**
     * 
     */
    private String gecomplr;

    /**
     * 
     */
    private String checker;

    /**
     * 
     */
    private String entDate;

    /**
     * 
     */
    private String begChgTy;

    /**
     * 
     */
    private String endChgTy;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Geometry getGeom() {
        return geom;
    }

    public void setGeom(Geometry geom) {
        this.geom = geom;
    }

    public String getNamePy() {
        return namePy;
    }

    public void setNamePy(String namePy) {
        this.namePy = namePy;
    }

    public String getNameCh() {
        return nameCh;
    }

    public void setNameCh(String nameCh) {
        this.nameCh = nameCh;
    }

    public String getNameFt() {
        return nameFt;
    }

    public void setNameFt(String nameFt) {
        this.nameFt = nameFt;
    }

    public Double getxCoor() {
        return xCoor;
    }

    public void setxCoor(Double xCoor) {
        this.xCoor = xCoor;
    }

    public Double getyCoor() {
        return yCoor;
    }

    public void setyCoor(Double yCoor) {
        this.yCoor = yCoor;
    }

    public String getPresLoc() {
        return presLoc;
    }

    public void setPresLoc(String presLoc) {
        this.presLoc = presLoc;
    }

    public String getTypePy() {
        return typePy;
    }

    public void setTypePy(String typePy) {
        this.typePy = typePy;
    }

    public String getTypeCh() {
        return typeCh;
    }

    public void setTypeCh(String typeCh) {
        this.typeCh = typeCh;
    }

    public String getLevRank() {
        return levRank;
    }

    public void setLevRank(String levRank) {
        this.levRank = levRank;
    }

    public Long getBegYr() {
        return begYr;
    }

    public void setBegYr(Long begYr) {
        this.begYr = begYr;
    }

    public String getBegRule() {
        return begRule;
    }

    public void setBegRule(String begRule) {
        this.begRule = begRule;
    }

    public Long getEndYr() {
        return endYr;
    }

    public void setEndYr(Long endYr) {
        this.endYr = endYr;
    }

    public String getEndRule() {
        return endRule;
    }

    public void setEndRule(String endRule) {
        this.endRule = endRule;
    }

    public Long getNoteId() {
        return noteId;
    }

    public void setNoteId(Long noteId) {
        this.noteId = noteId;
    }

    public String getObjType() {
        return objType;
    }

    public void setObjType(String objType) {
        this.objType = objType;
    }

    public Long getSysId() {
        return sysId;
    }

    public void setSysId(Long sysId) {
        this.sysId = sysId;
    }

    public String getGeoSrc() {
        return geoSrc;
    }

    public void setGeoSrc(String geoSrc) {
        this.geoSrc = geoSrc;
    }

    public String getCompiler() {
        return compiler;
    }

    public void setCompiler(String compiler) {
        this.compiler = compiler;
    }

    public String getGecomplr() {
        return gecomplr;
    }

    public void setGecomplr(String gecomplr) {
        this.gecomplr = gecomplr;
    }

    public String getChecker() {
        return checker;
    }

    public void setChecker(String checker) {
        this.checker = checker;
    }

    public String getEntDate() {
        return entDate;
    }

    public void setEntDate(String entDate) {
        this.entDate = entDate;
    }

    public String getBegChgTy() {
        return begChgTy;
    }

    public void setBegChgTy(String begChgTy) {
        this.begChgTy = begChgTy;
    }

    public String getEndChgTy() {
        return endChgTy;
    }

    public void setEndChgTy(String endChgTy) {
        this.endChgTy = endChgTy;
    }
}