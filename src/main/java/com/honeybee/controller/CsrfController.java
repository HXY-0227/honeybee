package com.honeybee.controller;

import com.honeybee.common.bean.HoneyResult;
import com.honeybee.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private final static Logger logger = LoggerFactory.getLogger(CsrfController.class);

    /**
     * 生成session，返回到前台
     */
    @GetMapping("")
    @ResponseBody
    public HoneyResult creatToken(HttpSession session) {
        logger.info("begin create csrfToken...");
        String token = Utils.createToken(128);
        session.setAttribute("token",token);
        logger.info("end create csrfToken...");
        return new HoneyResult(token);

    }
}
