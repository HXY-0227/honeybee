package com.honeybee.common.exception;

import com.honeybee.common.bean.HoneyResult;
import com.honeybee.common.bean.ResultCode;
import com.honeybee.common.bean.UserBean;
import com.honeybee.utils.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * 全局异常处理类
 * @author HXY
 * @version 1.0
 */
@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //日志处理类
    private final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * Exception异常处理
     * @param e
     * @return HoneyResult
     */
    @ExceptionHandler(value = Exception.class)
    @ResponseBody
    public HoneyResult handleException(Exception e) {

        logger.error(e.getMessage());
        logger.error(ExceptionUtil.getStackTrace(e));
        return HoneyResult.build(HoneyResult.ERR_STATE,e.getMessage());
    }

    /**
     * 参数无效的异常处理
     * @param e
     * @return
     */
    @ExceptionHandler(value = InvalidException.class)
    @ResponseBody
    public HoneyResult handleInvalidException(InvalidException e) {
        logger.error("parameter is invalid");
        logger.error(e.getMessage());

        return HoneyResult.build(e.getCode(),e.getMessage());
    }

}
