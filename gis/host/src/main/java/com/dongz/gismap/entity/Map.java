package com.dongz.gismap.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName v6_time_cnty_pts_utf_wgs84
 */
@TableName(value ="v6_time_cnty_pts_utf_wgs84")
@Data
public class Map implements Serializable {
    /**
     * 
     */
    @TableId
    private Integer id;

    /**
     * 
     */
    private Object geom;

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
}