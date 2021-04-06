package com.learn.dynamicdatasource.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * null
 * @TableName data_source
 */
@TableName(value ="data_source")
@Data
public class DataSource implements Serializable {
    /**
     * 
     */
    private Long id;

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private String driver;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}