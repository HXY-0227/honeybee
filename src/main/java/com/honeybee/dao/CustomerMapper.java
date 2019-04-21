package com.honeybee.dao;

import com.honeybee.common.bean.CustomerBean;
import com.honeybee.common.bean.HoneyResult;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CustomerMapper {

    /**
     * 添加vip客户
     * @return 是否添加成功的结果
     */
    public HoneyResult addCustomer(CustomerBean customer);
}
