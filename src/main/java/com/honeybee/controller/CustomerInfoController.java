package com.honeybee.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.honeybee.common.bean.CustomerInfoBean;
import com.honeybee.common.bean.HoneyResult;
import com.honeybee.service.CustomerInfoService;
import com.honeybee.utils.RedisUtil;
import com.honeybee.utils.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * Package: com.honeybee.controller
 * Description：
 * Date: Created in 2019/4/20 22:50
 * Company: haier
 */

@RestController
@RequestMapping(value = "/userAccount")
public class CustomerInfoController {


    private final static Logger logger = LoggerFactory.getLogger(CustomerInfoController.class);
    private final static String baseKey = "REDIS:USER:SESSION:KEY:";

    @Autowired
    private CustomerInfoService customerInfoService;

    /**
     * @return HoneyResult
     * @Description 获取商家报表
     * @params request  请求
     * @param requestBody  time:day(天)，week(周)，month(月)，threeMonth(近三月)，year(年)
     * @createDate 2019/4/20
     */
    @RequestMapping(value = "/query/total/money", method = RequestMethod.POST)
    public HoneyResult queryUserTotalMoney(HttpServletRequest request, @RequestBody String requestBody) {
        String token = request.getHeader("token");
        JSONObject requestData = JSON.parseObject(requestBody);
        String timeType = requestData.getString("timeType");
        RedisUtil redis = SpringContextUtil.getInstance().getBeanByClass(RedisUtil.class);
        String userId = String.valueOf(redis.get(baseKey + token));
        CustomerInfoBean userInfoBean = customerInfoService.queryUserTotalMoney(userId,timeType);
        return HoneyResult.ok(userInfoBean);
    }
}

