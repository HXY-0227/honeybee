package com.honeybee.service;

import com.honeybee.common.bean.CustomerInfoBean;

/**
 * Package: com.honeybee.service
 * Description：
 * Date: Created in 2019/4/20 23:29
 * Company: haier
 */
public interface CustomerInfoService {

    /**
     * @return String
     * @Description 查询用户账户金额
     * @params userId 用户userId
     * @createDate 2019/4/20
     */
    public CustomerInfoBean queryUserTotalMoney(String userId, String timeType);
}
