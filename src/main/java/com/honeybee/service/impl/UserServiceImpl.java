package com.honeybee.service.impl;

import com.honeybee.common.bean.HoneyResult;
import com.honeybee.common.bean.UserBean;
import com.honeybee.dao.UserMapper;
import com.honeybee.service.UserService;
import com.honeybee.utils.HoneybeeConstants;
import com.honeybee.utils.IDUtil;
import com.honeybee.utils.PasswordHash;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;

/**
 * @author HXY
 * @version 1.0
 */
@Service(value = "userService")
public class UserServiceImpl implements UserService {

    // userId
    private static final String USER_ID = "USER_ID";

    // 用户名最大长度
    private static final int MAX_LENGTH = 128;

    // 用户名正则表达式，包含数字字母汉字
    private static final String USER_NAME_REGEX = "^(?!_)(?!.*?_)[a-zA-Z0-9_\\u4e00-\\u9fa5]+$";

    // 密码正则表达式，包含6-12位字母数字组合
    private static final String PASSWORD_REGEX = "^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z_]{6,12}$";

    // 电话号码正则表达式
    private static final String PHONE_REGEX = "^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8}$";

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
    public HoneyResult userLogin(String userName, String password) throws Exception {
        // 查询用户信息
        UserBean user = userMapper.selectUserByName(userName);

        // 没有用户
        if (null == user) {
            return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                    "username or password error");
        }

        // 校验密码
        if (PasswordHash.validatePassword(password, user.getPassword())) {
            return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                    "username or password error");
        }

        return HoneyResult.ok();
    }

    /**
     * 用户数据校验，在用户输入后就立马校验
     * @param param 待校验信息
     * @param type 校验类型
     * @return 校验结果
     */
    public HoneyResult checkUser(String param, Integer type) {

        // 校验用户名
        if (type == HoneybeeConstants.UserCode.CHECK_USERNAME) {

            // 用户名不能大于128位
            if (param.length() > MAX_LENGTH) {

                logger.info("username checked failure...");
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "username does't allowed more than 128 characters");
            }

            // 校验用户名内容
            if (!param.matches(USER_NAME_REGEX)) {
                logger.info("username checked failure...");
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "username does't contain illegal characters");
            }
        }

        // 校验密码
        if (type == HoneybeeConstants.UserCode.CHECK_PASSWORD) {

            // 校验密码只能包含字母数字，且长度介于6-12
            if (!param.matches(PASSWORD_REGEX)) {
                logger.info("password checked failure...");
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "password can only contain numbers and letters and length between 6 and 12");
            }
        }

        // 校验电话号码
        if (type == HoneybeeConstants.UserCode.CHECK_PHONE) {

            if (!param.matches(PHONE_REGEX)) {
                logger.info("phone checked failure...");
                return HoneyResult.build(HoneybeeConstants.HttpStatusCode.BAD_REQUEST.getCode(),
                        "please input correct phone number");
            }
        }

        logger.info("param check successfully...");
        return HoneyResult.ok();
    }

}
