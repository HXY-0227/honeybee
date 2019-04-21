package com.honeybee.service.impl;

import com.honeybee.common.bean.CustomerBean;
import com.honeybee.common.bean.HoneyResult;
import com.honeybee.dao.CustomerMapper;
import com.honeybee.service.CustomerService;
import com.honeybee.utils.IDUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 顾客处理接口的实现类
 * @author HXY
 * @version
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerMapper customerMapper;

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);

    // 客户ID
    private static final String CUSTOMER_ID = "CUSTOMER_ID";

    /**
     * 添加顾客
     * @param customer 顾客信息
     * @return 添加结果，是否成功
     */
    @Override
    public HoneyResult addCustomer(CustomerBean customer) {

        // customerId
        String customerId = IDUtil.createId(CUSTOMER_ID);

        // 补全客户信息
        customer.setCustomerId(customerId);
        customer.setCreateTime(new Date());
        customer.setUpdateTime(new Date());

        customerMapper.addCustomer(customer);

        logger.info("add customer successfully...");
        return HoneyResult.ok();
    }

    /**
     * 校验客户信息
     * @param param 带校验参数
     * @param type 校验类型
     * @return 校验结果
     */
    @Override
    public HoneyResult checkCustomer(String param, Integer type) {


        return HoneyResult.ok();
    }




}
