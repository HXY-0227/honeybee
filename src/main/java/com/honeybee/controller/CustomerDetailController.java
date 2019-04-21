package com.honeybee.controller;

import com.honeybee.common.bean.HoneyResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import com.honeybee.service.CustomerDetailService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author JISON
 * @title: CustomerController
 * @projectName honeybee
 * @description: 上传客户消费明细信息
 * @date 2019/4/21 00211:20
 */

@RestController
@RequestMapping("/honeybee")
public class CustomerDetailController {
    /**
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */

    @Autowired
    private CustomerDetailService customerDetailService;


    @PostMapping("/customer/uploadCustomer")
    public HoneyResult uploadCustomerdetail(String customerId, Double money)
            throws Exception {

        return customerDetailService.addCustomerDeatil(customerId,money);
    }

    @PostMapping("/customer/findCustomerInfoByCustomerId")
    public HoneyResult findCustomerInfoByCustomerId(String customerId)
            throws Exception {

        return customerDetailService.findCustomerInfoByCustomerId(customerId);
    }

    @PostMapping("/customer/addCustomerDeatil")
    public HoneyResult addCustomerDeatil(String customerId, Double money)
            throws Exception {

        return customerDetailService.addCustomerDeatil(customerId, money);
    }


}
