package com.honeybee.dao;

import com.honeybee.common.bean.CustomerBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {

    /**
     * 添加vip客户
     * @return 是否添加成功的结果
     */
    public void addCustomer(CustomerBean customer);

    /**
     * 查询用户
     * @param customerName 客户名
     * @return 查询结果
     */
    public String selectCustomerByName(String customerName);
}
