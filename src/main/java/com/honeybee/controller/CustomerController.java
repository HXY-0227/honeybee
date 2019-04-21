package com.honeybee.controller;

import com.honeybee.common.bean.CustomerBean;
import com.honeybee.common.bean.HoneyResult;
import com.honeybee.service.CustomerService;
import com.honeybee.utils.HoneybeeConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 客户处理controller
 * @author HXY
 * @version 1.0
 */
@RestController
@RequestMapping("/honeybee/customer")
public class CustomerController {

    // 日志
    private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @RequestMapping("add")
    public HoneyResult addCustomer(@RequestBody CustomerBean customer) {

        return customerService.addCustomer(customer);
    }

    /**
     * 校验客户信息
     * @param param 待校验内容
     * @param type 校验类型
     * @return 校验结果
     */
    @GetMapping("/check/{param}/{type}")
    public HoneyResult checkCustomer(@PathVariable String param, @PathVariable Integer type) {

        // 判断校验内容是否为空
        if (StringUtils.isBlank(param)) {
            logger.info("check param is null...");
            return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                    "check param is not allowed null");
        }

        // 判断校验类型是否为空
        if (null == type) {
            logger.info("check type is null...");
            return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                    "check type is not allowed null");
        }

        // 判断校验类型是否为用户名、电话、金额
        if (type != HoneybeeConstants.CheckCode.CHECK_USERNAME
                && type != HoneybeeConstants.CheckCode.CHECK_PHONE
                && type != HoneybeeConstants.CheckCode.CHECK_MONEY) {
            logger.info("check type error");
            return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                    "check param error");
        }

        return customerService.checkCustomer(param, type);
    }
}
