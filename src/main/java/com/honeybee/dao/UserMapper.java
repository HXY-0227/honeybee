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

}
