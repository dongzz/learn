package com.learn.dynamicdatasource.tools;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/4/6 2:52 下午
 */
@Data
@AllArgsConstructor
public class Res {
    private Integer status;
    private String message;
    private Object data;

    public static Res success() {
        return new Res(200, null, null);
    }
    public static Res success(String message) {
        return new Res(200, message, null);
    }
    public static Res success(Object data) {
        return new Res(200, null, data);
    }
    public static Res success( String message,Object data) {
        return new Res(200, message, data);
    }
    public static Res error(String message) {
        return new Res(500, message, null);
    }
    public static Res error(Integer errorCode,  String message) {
        return new Res(errorCode, message, null);
    }
}
