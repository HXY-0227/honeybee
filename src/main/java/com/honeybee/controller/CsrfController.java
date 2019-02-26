package com.honeybee.controller;

import com.honeybee.common.bean.HoneyResult;
import com.honeybee.utils.Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * CsrfController 返回token到前台
 * @author HXY
 * @version 1.0
 */
@RestController
public class CsrfController {

    /**
     * 生成session，返回到前台
     */
    @GetMapping("")
    @ResponseBody
    public HoneyResult creatToken(HttpSession session) {
        String token = Utils.createToken(128);
        session.setAttribute("token",token);
        return new HoneyResult(token);

    }
}
