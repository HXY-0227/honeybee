package com.honeybee.controller;

import com.honeybee.common.bean.HoneyResult;
import com.honeybee.common.bean.UserBean;
import com.honeybee.service.UserService;
import com.honeybee.utils.HoneybeeConstants;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    /**
     * 用户登录
     * @param userName 用户名
     * @param password 用户密码
     * @return 登录结果
     */
    @PostMapping("/user/login")
    public HoneyResult userLogin(String userName, String password) throws Exception {

        return userService.userLogin(userName, password);
    }
    /**
     * 用户注册
     * @return 注册结果
     */
    @PostMapping("/user/register")
    public HoneyResult userRegister(@RequestBody UserBean user) throws Exception {

        HoneyResult honeyResult = userService.userRegister(user);
        return honeyResult;
    }

    /**
     * ajax校验用户输入
     * @param param 待校验的参数
     * @param type 校验类型
     * @return 校验结果
     */
    @GetMapping("/check/{param}/{type}")
    public HoneyResult checkUser(@PathVariable String param, @PathVariable Integer type) {

        // 判断校验内容是否为空
        if (StringUtils.isBlank(param)) {
            logger.info("check param is null...");
            return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                    "check param is not allowed null");
        }

        // 判断校验类型是否为空
        if (null == type) {
            logger.info("check type is null...");
            return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                    "check type is not allowed null");
        }

        // 判断校验类型是否为用户名、电话、密码
        if (type != HoneybeeConstants.UserCode.CHECK_USERNAME
                && type != HoneybeeConstants.UserCode.CHECK_PASSWORD
                && type != HoneybeeConstants.UserCode.CHECK_PHONE) {
            logger.info("check type error");
            return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                    "check param error");
        }

        // 调用service层校验
        return userService.checkUser(param, type);

    }

}
