package com.dongz.gismap.util;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/5/7 11:36 上午
 */
public class Res {
    private Integer code;
    private String msg;
    private Object data;

    public Res(Integer code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static Res success() {
        return success("ok");
    }

    public static Res success(String msg) {
        return new Res(200, msg, null);
    }

    public static Res success(Object data) {
        return new Res(200, "ok", data);
    }

    public static Res error(String error) {
        return new Res(200, error, null);
    }
}
