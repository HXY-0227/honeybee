package com.honeybee.controller;

import com.honeybee.common.bean.HoneyResult;
import com.honeybee.common.bean.UserBean;
import com.honeybee.service.UserService;
import com.honeybee.utils.IDUtil;
import com.honeybee.utils.JsonUtil;
import com.honeybee.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.security.SecureRandom;

/**
 * userController
 * @author HXY
 * @version 1.0
 */
@RestController
@RequestMapping("/honeybee")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisUtil redis;

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    /*@GetMapping("/select")
    @ResponseBody
    public Object xxxx() {

        IDUtil util = new IDUtil();
        logger.info("开始set....");
        //Long userId = util.createId("userId");
        //logger.info("get...：" + userId);
        return null;

    }

    *//**
     * 用户注册
     * @return
     *//*
    @PostMapping("/user/register")
    @ResponseBody
    public HoneyResult userRegister(UserBean user) throws Exception {

        HoneyResult honeyResult = userService.userRegister(user);
        return honeyResult;
    }*/

    /**
     * ajax校验用户输入
     * @param param 待校验的参数
     * @param type 校验类型
     * @return 校验结果
     */
    @GetMapping("/check/{param}/{type}")
    @ResponseBody
    public HoneyResult checkUser(@PathVariable String param, @PathVariable Integer type) {

        HoneyResult result = null;

        // 判断校验内容是否为空
        if (StringUtils.isBlank(param)) {
            result = HoneyResult.build(400, "check param is not allowed null");
        }

        // 判断校验类型是否为空
        if (null == type) {
            result = HoneyResult.build(400,"check type is not allowed null");
        }

        // 判断校验类型是否为用户名、电话、邮箱、密码   需要封装成枚举类型的常量
        if (type != 1 && type != 2 && type != 3 && type != 4) {
            result = HoneyResult.build(400,"check param error");
        }

        // 调用service层校验
        result = userService.checkUser(param, type);

        return result;
    }

}
