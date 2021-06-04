package com.dongz.activiti.utils;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author dz <895180729@qq.com>
 * @Description
 * @Version V1.0.0
 * @Since 1.8
 * @Date 2021/5/26 5:27 下午
 */
@Data
@AllArgsConstructor
public class Res {
    private Integer code;
    private String msg;
    private Object data;

    public static Res ok() {
        return new Res(200, "success", null);
    }

    public static Res ok(String msg) {
        return new Res(200, msg, null);
    }

    public static Res ok(Object data) {
        return new Res(200, "success", data);
    }

    public static Res error() {
        return new Res(500, "error", null);
    }
}
