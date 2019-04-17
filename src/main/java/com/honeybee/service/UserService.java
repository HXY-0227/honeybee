package com.honeybee.service;

import com.honeybee.common.bean.HoneyResult;
import com.honeybee.common.bean.UserBean;
import org.apache.commons.lang3.StringUtils;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

/**
 * @author HXY
 * @version 1.0
 */
public interface UserService {


    /**
     * 用户注册
     * @param user 用户信息
     * @return 是否注册成功的结果
     */
    public HoneyResult userRegister(UserBean user) throws Exception;

    /**
     * 用户登录
     * @param userName 用户名
     * @param password 用户密码
     */
    public HoneyResult userLogin(String userName, String password) throws Exception;

    /**
     * 校验用户输入
     * @param param 待校验参数
     * @param type 校验类型
     * @return 校验结果
     */
    public HoneyResult checkUser(String param, Integer type);


}
