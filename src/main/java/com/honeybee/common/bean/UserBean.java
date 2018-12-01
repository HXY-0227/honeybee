package com.honeybee.common.bean;

import lombok.Data;

import java.util.Date;

@Data
public class UserBean {

    /**
     *  用户Id
     */
    private String userId;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户昵称
     */
    private String name;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
