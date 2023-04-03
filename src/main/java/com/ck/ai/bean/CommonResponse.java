package com.ck.ai.bean;

import lombok.Data;

@Data
public class CommonResponse {
    private String code;
    private Object data;
    private String msg;
    private boolean success;

    public CommonResponse(String code, Object data, String msg, boolean success) {
        this.code = code;
        this.data = data;
        this.msg = msg;
        this.success = success;
    }

    public CommonResponse(Object data, String msg) {
        this.code = "200";
        this.data = data;
        this.msg = msg;
        this.success = true;
    }
}
