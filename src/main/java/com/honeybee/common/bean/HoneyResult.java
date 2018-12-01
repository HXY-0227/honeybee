package com.honeybee.common.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 自定义响应结构
 */
@NoArgsConstructor
@Data
public class HoneyResult {

    //自定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //响应状态码
    private Integer status;

    //响应业务状态
    private String msg;

    //响应中的数据
    private Object data;

    public HoneyResult(Object data) {
        this.status = 200;
        this.msg = "OK";
        this.data = data;
    }

    public HoneyResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public static HoneyResult ok() {
        return new HoneyResult(null);
    }

    public static HoneyResult build(Integer status, String msg) {
        return new HoneyResult(new HoneyResult(status, msg, null));
    }

    public static HoneyResult build(Integer status, String msg, Object data) {
        return new HoneyResult(new HoneyResult(status, msg, data));
    }

}
