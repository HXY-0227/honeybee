package com.honeybee.common.bean;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.honeybee.utils.HoneybeeConstants;
import lombok.Data;

import java.io.Serializable;

/**
 * 自定义响应结构
 * @author HXY
 * @version 1.0
 */
@Data
public class HoneyResult implements Serializable {

    private static final long serialVersionUID = -570683798213903190L;

    //自定义jackson对象
    private static final ObjectMapper MAPPER = new ObjectMapper();

    //响应状态码
    private Integer status;

    //响应业务状态
    private String msg;

    //响应中的数据
    private Object data;

    /**
     * 正常的构造函数
     * @param data 返回的数据
     */
    public HoneyResult(Object data) {
        this.status = HoneybeeConstants.HttpStatusCode.OK.getCode();
        this.msg = HoneybeeConstants.HttpStatusCode.OK.getMessage();
        this.data = data;
    }

    /**
     * 异常的构造函数
     * @param status 状态码
     * @param msg 响应信息
     * @param data 返回的数据
     */
    public HoneyResult(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    /**
     * 正常不返回数据
     * @return
     */
    public static HoneyResult ok() {
        return new HoneyResult(null);
    }

    /**
     * 异常不返回数据
     * @return
     */
    public static HoneyResult build(Integer status, String msg) {
        return new HoneyResult(status, msg, null);
    }

    /**
     * 异常返回数据
     * @return
     */
    public static HoneyResult build(Integer status, String msg, Object data) {
        return new HoneyResult(status, msg, data);
    }

}
