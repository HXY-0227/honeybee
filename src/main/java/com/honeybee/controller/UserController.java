package com.honeybee.controller;

import com.honeybee.common.bean.UserBean;
import com.honeybee.common.database.DynamicDataSourceContextHolder;
import com.honeybee.common.database.TargetDataSource;
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
    private UserService service;

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    /*@PostMapping("/register")
    public HoneyResult register(UserBean user){
        try {
            HoneyResult result =  service.register(user);
            return result;
        }catch (Exception e) {
            return HoneyResult.build(400,ExceptionUtil.getStackTrace(e));
        }
    }*/

    @GetMapping("/select")
    @TargetDataSource("master")
    public UserBean xxxx(){
        try {
            UserBean result =  service.select();
            logger.error(DynamicDataSourceContextHolder.getDataSourceKey()+"22");
            return result;
        }catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }
    }


}
