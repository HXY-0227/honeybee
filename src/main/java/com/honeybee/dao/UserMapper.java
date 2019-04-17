package com.honeybee.dao;

import com.honeybee.common.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author HXY
 * @version 1.0
 */
@Mapper
public interface UserMapper {

    /**
     * 注册用户
     * @param user
     * @return
     */
    int userRegister(UserBean user);

    /**
     * 根据用户名查找用户
     * @param userName 用户名
     * @return user 用户对象
     */
    UserBean selectUserByName(String userName);

}
