package com.honeybee.utils;

import lombok.Getter;
import lombok.Setter;

/**
 * 常量类
 * @author HXY
 */
public final class HoneybeeConstants {

    /**
     * http响应状态码
     */
    public static enum HttpStatusCode {
        OK(200, "OK"),
        BAD_REQUEST(400, "BAD_REQUEST"),
        SERVER_ERROR(500, "SERVER_ERROR");

        //错误码
        @Getter
        @Setter
        private final Integer code;

        @Getter
        @Setter
        //错误描述
        private final String message;

        HttpStatusCode(Integer code, String message) {
            this.code = code;
            this.message = message;
        }
    }

    /**
     * 用户code
     */
    public static class UserCode {
        public static final int CHECK_USERNAME = 1;
        public static final int CHECK_PASSWORD = 2;
        public static final int CHECK_PHONE = 3;
    }
}
