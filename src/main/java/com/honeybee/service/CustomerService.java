package com.honeybee.service;

import com.honeybee.common.bean.CustomerBean;
import com.honeybee.common.bean.HoneyResult;

/**
 * 顾客处理接口
 * @author HXY
 * @version 1.0
 */
public interface CustomerService {

    /**
     * 校验客户信息是否合法
     * @param param 带校验参数
     * @param type 校验类型
     * @return 校验结果
     */
    public HoneyResult checkCustomer(String param, Integer type);

    /**
     * 添加顾客
     * @param customer 顾客信息
     * @return 添加结果，是否成功
     */
    public HoneyResult addCustomer(CustomerBean customer);

}
