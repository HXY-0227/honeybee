package com.honeybee.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author HXY
 * @version 1.0
 */
@Data
public class UserBean implements Serializable {

    private static final long serialVersionUID = -548072219559017078L;
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
