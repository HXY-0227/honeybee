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
    public static class CheckCode {
        // 校验用户名
        public static final int CHECK_USERNAME = 1;
        // 校验密码
        public static final int CHECK_PASSWORD = 2;
        // 校验手机号
        public static final int CHECK_PHONE = 3;
        // 校验金额
        public static final int CHECK_MONEY = 4;
    }

    public static class Regex {
        // 用户名最大长度
        public static final int MAX_LENGTH = 128;

        // 用户名正则表达式，包含数字字母汉字
        public static final String USER_NAME_REGEX = "^(?!_)(?!.*?_)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";

        // 密码正则表达式，包含6-12位字母数字组合
        public static final String PASSWORD_REGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,12}$";

        // 电话号码正则表达式
        public static final String PHONE_REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";

        // 金额正则表达式
        public static final String MONEY_REGEX = "^(([1-9]{1}\\\\d*)|([0]{1}))(\\\\.(\\\\d){0,2})?$";
    }

}
