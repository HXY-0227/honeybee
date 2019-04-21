package com.honeybee.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.honeybee.common.bean.CustomerBean;
import com.honeybee.common.bean.CustomerDetailBean;
import com.honeybee.common.bean.HoneyResult;
import com.honeybee.dao.CustomerDetailMapper;
import com.honeybee.service.CustomerDetailService;
import com.honeybee.utils.HoneybeeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * @author JISON
 * @title: CustomerDetailServiceImpl
 * @projectName honeybee
 * @description: TODO
 * @date 2019/4/21 00213:48
 */



@Service(value = "customerDetailService")
public class CustomerDetailServiceImpl implements CustomerDetailService {

    @Autowired
    private CustomerDetailMapper customerDetailMapper;

    private static final Logger logger = LoggerFactory.getLogger(CustomerDetailServiceImpl.class);

    /**
     *  上传消费明细
     * @param id
     * @param money
     * @return
     */
    @Override
    public HoneyResult addCustomerDeatil(String id, Double money) throws Exception {

        CustomerDetailBean customerDetailBean = new CustomerDetailBean();
        customerDetailBean.setCustomerId(id);
        customerDetailBean.setMoney(money);
        customerDetailBean.setCreateTime(new Date());
        customerDetailMapper.addCustomerDeatil(customerDetailBean);

        return HoneyResult.ok();
    }

    /**
     * 更新客户余额信息
     * @param id
     * @param money
     * @return
     * @throws Exception
     */
    public HoneyResult updateCustomerInfo(String id, Double money) throws Exception {

        if(StringUtils.isEmpty(id)){
            logger.info("客户Id不能为空");
            return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                    "客户ID不能为空");
        }
        int result = customerDetailMapper.updateCustomerInfo(id,money);
        return HoneyResult.ok();
    }

    /**
     * 查询客户余额信息
     * @param id
     * @return
     * @throws Exception
     */
    public CustomerBean findCustomerInfoByCustomerId(String id) throws Exception{

        CustomerBean customerInfo = customerDetailMapper.findCustomerInfoByCustomerId(id);

        return customerInfo;
    }
}
