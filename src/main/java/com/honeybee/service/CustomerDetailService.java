package com.honeybee.service;

import com.honeybee.common.bean.CustomerBean;
import com.honeybee.common.bean.HoneyResult;

/**
 * @author JISON
 * @title: CustomerDetailService
 * @projectName honeybee
 * @description: 客户消费明细
 * @date 2019/4/21 00211:54
 */
public interface CustomerDetailService {

    /**
     * 上传消费明细
     * @param id
     * @param money
     * @return
     */

    public HoneyResult addCustomerDetail(String id, Double money);

    /**
     * 更新客户余额信息
     * @param id
     * @return
     * @throws Exception
     */
    public HoneyResult updateCustomerInfo(String id, Double money);

    /**
     * 查询客户余额信息
     * @param customerId
     * @return
     * @throws Exception
     */
    public CustomerBean findCustomerInfoByCustomerId(String customerId);
}
