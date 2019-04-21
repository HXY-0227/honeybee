package com.honeybee.common.bean;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

/**
 * @author JISON
 * @title: CustomerDetailBean
 * @projectName honeybee
 * @description: 客户流水信息
 * @date 2019/4/21 00211:31
 */
@Setter
@Getter
public class CustomerDetailBean {

    //客户ID
    private String customerId;

    //客户消费金额
    private Double money;

    //创建时间
    private Date createTime;


}
