package com.honeybee.controller;

import com.honeybee.common.bean.HoneyResult;
import com.honeybee.common.bean.UserBean;
import com.honeybee.service.UserService;
import com.honeybee.utils.ExceptionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    @ResponseBody
    public HoneyResult register(UserBean user){
        try {
            HoneyResult result =  service.register(user);
            return result;
        }catch (Exception e) {
            return HoneyResult.build(400,ExceptionUtil.getStackTrace(e));
        }
    }
}
