package com.honeybee.dao;

import com.honeybee.common.bean.UserBean;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

    /**
     * 注册用户
     * @param user
     * @return
     */
    //int insertUser(UserBean user);

    /**
     * 根据用户名查找用户
     * @param userNme
     * @return
     */
    UserBean selectByUserName(String name);

    /**
     * 根据电话查找用户
     * @param phone
     * @return
     */
    //UserBean selectByUserPhone(String phone);

}
