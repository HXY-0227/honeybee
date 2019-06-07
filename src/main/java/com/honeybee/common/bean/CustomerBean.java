package com.honeybee.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * vip客户
 * @author HXY
 */
@Data
@ApiModel(value = "客户信息")
public class CustomerBean implements Serializable {

    @ApiModelProperty(value = "商家Id", required = true)
    private String userId;

    @ApiModelProperty(value = "顾客Id", required = true)
    private String customerId;

    @ApiModelProperty(value = "顾客姓名", required = true)
    private String customerName;

    @ApiModelProperty(value = "顾客电话", required = true)
    private String customerPhone;

    @ApiModelProperty(value = "顾客充值总金额", required = true)
    private double totalMoney;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;

    @ApiModelProperty(value = "更新时间")
    private Date updateTime;

}
