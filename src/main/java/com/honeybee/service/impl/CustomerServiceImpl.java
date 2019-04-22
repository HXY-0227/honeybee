package com.honeybee.service.impl;

import com.honeybee.common.bean.CustomerBean;
import com.honeybee.common.bean.HoneyResult;
import com.honeybee.common.bean.UserBean;
import com.honeybee.dao.CustomerMapper;
import com.honeybee.service.CustomerService;
import com.honeybee.utils.HoneybeeConstants;
import com.honeybee.utils.IDUtil;
import org.apache.commons.lang3.StringUtils;
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

        // 校验用户名
        if (type == HoneybeeConstants.CheckCode.CHECK_USERNAME) {

            String customId = customerMapper.selectCustomerByName(param);

            // 用户名是否存在
            if (!StringUtils.isBlank(customId)) {
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "username already exist...");
            }

            // 用户名不能大于128位
            if (param.length() > HoneybeeConstants.Regex.MAX_LENGTH) {

                logger.info("username checked failure...");
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "username does't allowed more than 128 characters");
            }

            // 校验用户名内容
            if (!param.matches(HoneybeeConstants.Regex.USER_NAME_REGEX)) {
                logger.info("username checked failure...");
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "username does't contain illegal characters");
            }
        }

        // 校验电话号码
        if (type == HoneybeeConstants.CheckCode.CHECK_PHONE) {

            if (!param.matches(HoneybeeConstants.Regex.PHONE_REGEX)) {
                logger.info("phone checked failure...");
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "please input correct phone number");
            }
        }

        // 校验电话号码
        if (type == HoneybeeConstants.CheckCode.CHECK_MONEY) {

            if (!param.matches(HoneybeeConstants.Regex.PHONE_REGEX)) {
                logger.info("phone checked failure...");
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "please input correct phone number");
            }
        }

        // 校验客户金额
        if (type == HoneybeeConstants.CheckCode.CHECK_MONEY) {

            if (!param.matches(HoneybeeConstants.Regex.MONEY_REGEX)) {
                logger.info("total money checked failure...");
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "please input correct money");
            }
        }

        logger.info("param check successfully...");
        return HoneyResult.ok();
    }




}
