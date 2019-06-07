package com.honeybee.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户
 * @author HXY
 * @version 1.0
 */
@Data
@ApiModel(value = "用户信息")
public class UserBean implements Serializable {

    private static final long serialVersionUID = -548072219559017078L;

    @ApiModelProperty(value = "用户Id", required = true)
    private String userId;

    @ApiModelProperty(value = "用户密码", required = true)
    private String password;

    @ApiModelProperty(value = "用户手机号", required = true)
    private String phone;

    @ApiModelProperty(value = "用户昵称", required = true)
    private String name;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
