package com.honeybee.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.honeybee.common.bean.CustomerBean;
import com.honeybee.common.bean.HoneyResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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


    @PostMapping("/customer/updateCustomer")
    public HoneyResult updateCustomerDetail(@RequestBody String requestBody){

        JSONObject requestData = JSON.parseObject(requestBody);
        String customerId = requestData.getString("customerId");
        double consume = Double.valueOf(requestData.getString("consume"));
        return customerDetailService.updateCustomerInfo(customerId,consume);
    }

    @PostMapping("/customer/findCustomerInfoByCustomerId")
    public HoneyResult findCustomerInfoByCustomerId(@RequestBody String requestBody){

        JSONObject requestData = JSON.parseObject(requestBody);
        String customerId = requestData.getString("customerId");
        CustomerBean customer = customerDetailService.findCustomerInfoByCustomerId(customerId);
        return HoneyResult.ok(customer);
    }

    @PostMapping("/customer/addCustomerDetail")
    public HoneyResult addCustomerDetail(@RequestBody String requestBody){

        JSONObject requestData = JSON.parseObject(requestBody);
        String customerId = requestData.getString("customerId");
        double consume = Double.valueOf(requestData.getString("consume"));
        return customerDetailService.addCustomerDetail(customerId, consume);
    }


}
