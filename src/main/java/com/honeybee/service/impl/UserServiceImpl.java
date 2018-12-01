package com.honeybee.service.impl;

import com.honeybee.common.bean.HoneyResult;
import com.honeybee.common.bean.UserBean;
import com.honeybee.dao.UserMapper;
import com.honeybee.service.UserService;
import com.honeybee.utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper mapper;

    /**
     * 检查数据是否可用
     * @return
     */
    @Override
    public HoneyResult checkData() {
        return null;
    }

    /**
     * 用户注册
     * @return
     */
    @Override
    public HoneyResult register(UserBean user) {
        try {
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            //密码加密存储
            user.setPassword(DigestUtils.md5DigestAsHex(user.getPassword().getBytes()));
            mapper.insertUser(user);
            return HoneyResult.ok();
        } catch (Exception e) {
            return HoneyResult.build(400, ExceptionUtil.getStackTrace(e));
        }

    }

    /**
     * 用户登录
     * @return
     */
    @Override
    public HoneyResult login(String phone, String password) {
        return null;
    }
}
