package com.honeybee.common.exception;

import lombok.Getter;
import lombok.Setter;

/**
 * 无效的参数异常类
 */
public class InvalidException extends RuntimeException {

    //自定义错误码
    @Getter
    @Setter
    private Integer code;

    //自定义构造器，必须输入状态码和异常信息
    public InvalidException(Integer code, String message) {
        super(message);
        this.code = code;
    }
}
