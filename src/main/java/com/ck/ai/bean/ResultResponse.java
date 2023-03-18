package com.ck.ai.bean;

import org.springframework.http.HttpStatus;

public class ResultResponse {
    private Object message;
    private int statusCode = HttpStatus.OK.value();

    public ResultResponse(Object message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public Object getMessage() {
        return message;
    }

    public void setMessage(Object message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }
}
