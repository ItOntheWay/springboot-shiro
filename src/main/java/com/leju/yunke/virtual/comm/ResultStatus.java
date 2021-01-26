package com.leju.yunke.virtual.comm;

public enum ResultStatus {
    OPERATE_FAIL(1000,"操作太频繁！"),
    PARAMA_NULL(1001,"参数不能为空！"),
    SAVE_FAIL(500,"保存失败"),
    DELETE_FAIL(501,"删除失败"),
    ;
    private Integer status;
    private String msg;



    ResultStatus(int status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    /**
     * 根据key获得对应value
     * */
    public static ResultStatus getResultStatusByKey(Integer state) {
        for (ResultStatus statusEnum : ResultStatus.values()) {
            if (state.intValue() == (statusEnum.getStatus())) {
                return statusEnum;
            }
        }
        return null;
    }
    /**
     * 根据value获得对应key
     * */
    public static ResultStatus getResultStatusByValue(String value) {
        for (ResultStatus statusEnum : ResultStatus.values()) {
            if (value.equalsIgnoreCase(statusEnum.getMsg())) {
                return statusEnum;
            }
        }
        return null;
    }
}
