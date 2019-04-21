package com.honeybee.common.bean;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * vip客户
 * @author HXY
 */
@Data
public class CustomerBean implements Serializable {


    /**
     * 商家Id
     */
    private String userId;

    /**
     * 顾客Id
     */
    private String customerId;

    /**
     * 顾客姓名
     */
    private String customerName;

    /**
     * 顾客电话
     */
    private String customerPhone;

    /**
     * 顾客充值总金额
     */
    private double totalMoney;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

}
