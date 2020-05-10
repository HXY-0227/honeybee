package com.honeybee.common.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
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
@Data
@ApiModel(value = "客户流水信息")
public class CustomerDetailBean {

    @ApiModelProperty(value = "客户Id", required = true)
    private String customerId;

    @ApiModelProperty(value = "客户消费金额", required = true)
    private Double money;

    @ApiModelProperty(value = "创建时间")
    private Date createTime;


}
