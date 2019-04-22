package com.honeybee.service.impl;

import com.honeybee.common.bean.CustomerInfoBean;
import com.honeybee.common.bean.CustomerYearInfoBean;
import com.honeybee.dao.CustomerInfoMapper;
import com.honeybee.service.CustomerInfoService;
import com.honeybee.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Package: com.honeybee.service.impl
 * Description：
 * Date: Created in 2019/4/20 23:31
 * Company: haier
 */
@Service
public class CustomerInfoServiceImpl implements CustomerInfoService {

    @Autowired
    private CustomerInfoMapper customerInfoMapper;

    @Override
    public CustomerInfoBean queryUserTotalMoney(String userId, String timeType) {
        CustomerInfoBean userInfoBean = null;
        switch (timeType) {
            case "day":
                userInfoBean = queryUserDayInfo(userId );
                break;
            case "week":
                userInfoBean = queryUserWeekInfo(userId );
                break;
            case "month":
                userInfoBean = queryUserMonthInfo(userId );
                break;
            case "threeMonth":
                userInfoBean = queryUserThreeMonthInfo(userId );
                break;
            case "year":
                userInfoBean = queryUserYearInfo(userId );
                break;
        }
        return userInfoBean;
    }

    private CustomerInfoBean queryUserDayInfo(String userId ) {
        CustomerInfoBean customerInfoBean = null;
        //查询总会员
        String totalMember = customerInfoMapper.queryTotalMember(userId);
        Map <String, String> day = DateUtils.getDay();
        day.put("userId", userId);
        customerInfoBean = customerInfoMapper.queryCustomerDayInfo(day);
        if (customerInfoBean != null) {
            customerInfoBean.setTotalMember(totalMember);
        }
        return customerInfoBean;

    }

    private CustomerInfoBean queryUserWeekInfo(String userId ) {
        CustomerInfoBean customerInfoBean = null;
        //查询总会员
        String totalMember = customerInfoMapper.queryTotalMember(userId);
        Map week = DateUtils.getWeek();
        week.put("userId", userId);
        customerInfoBean = customerInfoMapper.queryCustomerInfo(week);
        if (customerInfoBean != null) {
            customerInfoBean.setTotalMember(totalMember);
        }

        return customerInfoBean;

    }

    private CustomerInfoBean queryUserMonthInfo(String userId ) {
        CustomerInfoBean customerInfoBean = null;
        //查询总会员
        String totalMember = customerInfoMapper.queryTotalMember(userId);
        Map <String, String> month = DateUtils.getMonth();
        month.put("userId", userId);
        customerInfoBean = customerInfoMapper.queryCustomerInfo(month);
        if (customerInfoBean != null) {
            customerInfoBean.setTotalMember(totalMember);
        }
        return customerInfoBean;

    }

    private CustomerInfoBean queryUserThreeMonthInfo(String userId ) {
        CustomerInfoBean customerInfoBean = null;
        //查询总会员
        String totalMember = customerInfoMapper.queryTotalMember(userId);
        Map <String, String> threeMonth = DateUtils.getThreeMonth();
        threeMonth.put("userId", userId);
        customerInfoBean = customerInfoMapper.queryCustomerInfo(threeMonth);
        if (customerInfoBean != null) {
            customerInfoBean.setTotalMember(totalMember);
        }
        return customerInfoBean;
    }

    private CustomerInfoBean queryUserYearInfo(String userId) {
        CustomerInfoBean customerInfoBean = new CustomerInfoBean();
        //查询总会员
        String totalMember = customerInfoMapper.queryTotalMember(userId);
        Map <String, String> year = DateUtils.getYear();
        year.put("userId", userId);
        List<CustomerYearInfoBean> customerInfoBeans = customerInfoMapper.queryCustomerYearInfo(year);
        customerInfoBean.setTotalMember(totalMember);
        customerInfoBean.setCustomerYearInfoBean(customerInfoBeans);
        return customerInfoBean;
    }
}
