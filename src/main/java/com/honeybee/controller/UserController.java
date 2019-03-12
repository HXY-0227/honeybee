package com.honeybee.controller;

import com.honeybee.common.bean.UserBean;
import com.honeybee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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

    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/select")
    @ResponseBody
    public UserBean xxxx() {

        logger.info("开始查询");
        UserBean result =  userService.select();
        logger.info("查询结束：" + result.toString());
        return result;

    }

}
