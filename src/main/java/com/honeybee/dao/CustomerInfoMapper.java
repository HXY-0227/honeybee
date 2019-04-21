package com.honeybee.dao;

import com.honeybee.common.bean.CustomerInfoBean;
import com.honeybee.common.bean.CustomerYearInfoBean;

import java.util.List;
import java.util.Map;

/**
 * Package: com.honeybee.dao
 * Description：
 * Date: Created in 2019/4/21 0:48
 * Company: haier
 */
public interface CustomerInfoMapper {

    /**
     * @return String 商家总会员数
     * @Description 查询商家总会员数
     * @params userId 商家id
     * @params timeType 时间类型
     * @createDate 2019/4/21
     */
    String queryTotalMember(String userId);



    CustomerInfoBean queryCustomerInfo(Map<String,String> map);


    List<CustomerYearInfoBean> queryCustomerYearInfo(Map<String,String> map);



}
