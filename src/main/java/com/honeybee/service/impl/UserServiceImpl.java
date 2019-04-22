package com.honeybee.service.impl;

import com.honeybee.common.bean.HoneyResult;
import com.honeybee.common.bean.UserBean;
import com.honeybee.dao.UserMapper;
import com.honeybee.service.UserService;
import com.honeybee.utils.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author HXY
 * @version 1.0
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    // userId
    private static final String USER_ID = "USER_ID";

    // token长度
    private static final int TOKEN_LENGTH = 16;

    // token名
    private static final String REDIS_USER_SESSION_KEY = "REDIS:USER:SESSION:KEY";

    // token过期时间
    private static final int TOKEN_EXPIRE = 1;

    // token名
    private static final String TOKEN_NAME = "HONEY_TOKEN";

    // log
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);


    @Autowired
    private UserMapper userMapper;

    /**
     * 用户注册
     * @param user 用户信息
     * @return 注册结果
     */
    @Override
    public HoneyResult userRegister(UserBean user) throws Exception {

        // userId
        String userId = IDUtil.createId(USER_ID);
        // 密码加密
        String passwordHash = PasswordHash.createHash(user.getPassword());
        // 补全用户信息
        user.setPassword(passwordHash);
        user.setUserId(userId);
        user.setCreateTime(new Date());
        user.setUpdateTime(new Date());

        // 持久化用户信息
        userMapper.userRegister(user);

        return HoneyResult.ok();
    }

    /**
     * 用户登录
     * @param userName 用户名
     * @param password 用户密码
     * @return
     */
    @Override
    public HoneyResult userLogin(String userName, String password, HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        // 获取RedisUtil实例
        RedisUtil redis = SpringContextUtil.getInstance().getBeanByClass(RedisUtil.class);

        // 查询用户信息
        UserBean user = userMapper.selectUserByName(userName);

        // 没有用户
        if (null == user) {
            return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                    "username or password error...");
        }

        // 校验密码
        if (!PasswordHash.validatePassword(password, user.getPassword())) {
            return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                    "username or password error...");
        }

        // 生成一个token
        String token = Utils.createToken(TOKEN_LENGTH);
        // 清空用户密码
        user.setPassword(null);
        // 将用户名存储在redis中
        redis.set(REDIS_USER_SESSION_KEY + ":" + token, user.getUserId());
        // 设置用户名过期时间
        redis.expire(REDIS_USER_SESSION_KEY + ":" + token, TOKEN_EXPIRE, TimeUnit.DAYS);
        // 将token设置到cookie中，带回客户端
        CookieUtil.doSetCookie(request, response, TOKEN_NAME, token);

        return HoneyResult.ok(token);
    }

    /**
     * 根据token获取用户信息
     * @param token
     * @return
     */
    @Override
    public HoneyResult getUserByToken(String token) {
        // 获取RedisUtil实例
        RedisUtil redis = SpringContextUtil.getInstance().getBeanByClass(RedisUtil.class);

        // 查询redis，获取用户id
        String userId = (String) redis.get(REDIS_USER_SESSION_KEY + ":" + token);

        if (StringUtils.isBlank(userId)) {
            return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                    "session expired");
        }

        return HoneyResult.ok(userId);
    }

    /**
     * 用户数据校验，在用户输入后就立马校验
     * @param param 待校验信息
     * @param type 校验类型
     * @return 校验结果
     */
    @Override
    public HoneyResult checkUser(String param, Integer type) {

        // 校验用户名
        if (type == HoneybeeConstants.CheckCode.CHECK_USERNAME) {

            UserBean result = userMapper.selectUserByName(param);

            // 用户名是否存在
            if (null != result) {
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "username already exist...");
            }

            // 用户名不能大于128位
            if (param.length() > HoneybeeConstants.Regex.MAX_LENGTH) {

                logger.info("username checked failure...");
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "username does't allowed more than 128 characters");
            }

            // 校验用户名内容
            if (!param.matches(HoneybeeConstants.Regex.USER_NAME_REGEX)) {
                logger.info("username checked failure...");
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "username does't contain illegal characters");
            }
        }

        // 校验密码
        if (type == HoneybeeConstants.CheckCode.CHECK_PASSWORD) {

            // 校验密码只能包含字母数字，且长度介于6-12
            if (!param.matches(HoneybeeConstants.Regex.PASSWORD_REGEX)) {
                logger.info("password checked failure...");
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "password can only contain numbers and letters and length between 6 and 12");
            }
        }

        // 校验电话号码
        if (type == HoneybeeConstants.CheckCode.CHECK_PHONE) {

            if (!param.matches(HoneybeeConstants.Regex.PHONE_REGEX)) {
                logger.info("phone checked failure...");
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "please input correct phone number");
            }
        }

        logger.info("param check successfully...");
        return HoneyResult.ok();
    }

}
