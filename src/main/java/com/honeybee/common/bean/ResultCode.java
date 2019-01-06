package com.honeybee.common.bean;

import lombok.Getter;
import lombok.Setter;

public enum ResultCode {

    INVALID_PARAMETER(1000,"parameter is invalid"),
    DATA_NOTFOUND(1001,"data not found");

    //错误码
    @Getter
    @Setter
    private final Integer code;

    @Getter
    @Setter
    //错误描述
    private final String message;

    ResultCode(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
