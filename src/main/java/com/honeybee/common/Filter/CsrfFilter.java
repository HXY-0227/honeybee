package com.honeybee.common.Filter;

import org.apache.ibatis.logging.LogFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 防止csrf攻击的过滤器
 * @author HXY
 */
@WebFilter(urlPatterns = "/*", filterName = "csrfFilter")
public class CsrfFilter extends OncePerRequestFilter {

    private final static String REQUEST_METHOD_POST = "POST";
    private final static String REQUEST_METHOD_GET = "GET";
    private final static Logger logger = LoggerFactory.getLogger(CsrfFilter.class);

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        logger.info("Begin csrf...");

        //服务器端存储的token
        String token_server = (String) request.getSession().getAttribute("token");
        //客户端请求的token
        String token_client = (String) request.getAttribute("token");

        //如果是post请求，则做csrf校验
        if (REQUEST_METHOD_POST.equals(request.getMethod())) {
            if (null != token_client && null != token_server && token_client.equals(token_server)) {
                filterChain.doFilter(request, response);
            }
            logger.info("Csrf parameter is invalid...");
        }else if (REQUEST_METHOD_GET.equals(request.getMethod())){
            filterChain.doFilter(request,response);
        }

        logger.info("End Csrf...");
    }
}
