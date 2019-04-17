package com.honeybee.controller;

import com.honeybee.common.bean.HoneyResult;
import com.honeybee.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

/**
 * CsrfController 生成token，存储在session中并返回到前台
 * @author HXY
 * @version 1.0
 */
@RestController
public class CsrfController {

    private final static Logger logger = LoggerFactory.getLogger(CsrfController.class);

    // token长度
    private final static int TOKEN_LENGTH = 128;

    // token
    private final static String TOKEN = "token";
    /**
     * 在服务端保存token，并将生成的token返回到前台
     * @param request
     * @return 生成的token
     */
    @GetMapping("/honeybee/csrf")
    @ResponseBody
    public HoneyResult createToken(HttpServletRequest request) {
        logger.info("begin create csrfToken...");
        String token = Utils.createToken(TOKEN_LENGTH);
        request.getSession().setAttribute(TOKEN, token);
        logger.info("end create csrfToken...");
        return new HoneyResult(token);

    }
}
