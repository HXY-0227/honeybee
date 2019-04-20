package com.honeybee.service;

import com.honeybee.common.bean.HoneyResult;
import com.honeybee.common.bean.UserBean;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
     * @param user 用户
     */
    public HoneyResult userLogin(UserBean user, HttpServletRequest request, HttpServletResponse response)
            throws Exception;

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    public HoneyResult getUserByToken(String token);

    /**
     * 校验用户输入
     * @param param 待校验参数
     * @param type 校验类型
     * @return 校验结果
     */
    public HoneyResult checkUser(String param, Integer type);


}
