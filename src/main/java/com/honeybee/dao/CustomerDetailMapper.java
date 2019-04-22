package com.honeybee.dao;

import com.honeybee.common.bean.CustomerBean;
import com.honeybee.common.bean.CustomerDetailBean;

/**
 * @author JISON
 * @title: CustomerDetailMapper
 * @projectName honeybee
 * @description: 客户消费明细查询、新增、修改
 * @date 2019/4/21 00212:03
 */
public interface CustomerDetailMapper {

    /**
     * 查询客户的余额信息
     * @param customerId  客户ID
     * @return
     */
    CustomerBean findCustomerInfoByCustomerId(String customerId);


    /**
     * 添加消费明细
     * @param customerDetailBean
     * @return
     */
    int addCustomerDetail(CustomerDetailBean customerDetailBean);

    /**
     * 修改用户余额信息
     * @return
     */
    int updateCustomerInfo(CustomerBean customerBean);

}
