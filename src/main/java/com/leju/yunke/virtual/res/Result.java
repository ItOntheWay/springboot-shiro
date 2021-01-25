package com.leju.yunke.virtual.res;

import com.leju.yunke.virtual.comm.ResultStatus;

import java.io.Serializable;

public class Result implements Serializable {
    public static final int SUCCESS = 0;
    public static final int FAILT = 500;

    private int code;
    private String message;
    private Object data;

    public Result() {
        this.setCode(0);
        this.setMessage("操作成功");
    }

    public Result(ResultStatus resultStatus) {
        this.setCode(resultStatus.getStatus());
        this.setMessage(resultStatus.getMsg());
    }

    public static Result ok() {
        Result r = new Result();
        r.setCode(0);
        return r;
    }

    public static Result error(int code, String msg) {
        Result r = new Result();
        r.setCode(code);
        r.setMessage(msg);
        return r;
    }

    public static Result error(Exception ex) {
        Result r = new Result();
        r.setCode(500);
        r.setMessage(ex.getMessage());
        return r;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
