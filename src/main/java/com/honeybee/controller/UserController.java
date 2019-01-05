package com.honeybee.controller;

import com.honeybee.common.bean.UserBean;
import com.honeybee.dao.UserMapper;
import com.honeybee.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/honeybee")
public class UserController {

    @Autowired
    private UserService userService;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @GetMapping("/select")
    public UserBean xxxx(){
        try {
            UserBean result =  userService.select();
            return result;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }


}
