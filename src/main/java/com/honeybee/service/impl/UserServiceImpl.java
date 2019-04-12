package com.honeybee.service.impl;

import com.honeybee.common.bean.HoneyResult;
import com.honeybee.common.bean.UserBean;
import com.honeybee.dao.UserMapper;
import com.honeybee.service.UserService;
import com.honeybee.utils.IDUtil;
import com.honeybee.utils.PasswordHash;
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

    // 日志
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    @Override
    public UserBean select() {
        try {
            return userMapper.selectByUserName("HXY");
        }catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    /**
     * 用户注册
     * @return
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

        return HoneyResult.ok();
    }

    /**
     * 用户数据校验，在用户输入后就立马校验
     * @param user 用户信息
     * @return 校验结果
     */
    private static HoneyResult checkUser(UserBean user) {


        String userName = user.getName();
        if (null == userName || userName.isEmpty()) {
            return HoneyResult.build(200, "username is not allowed null", null);
        }

        if (userName.length() > MAX_LENGTH) {
            return HoneyResult.build(200,"username is not allowed more than 128 characters", null);
        }


        return null;
    }
}
