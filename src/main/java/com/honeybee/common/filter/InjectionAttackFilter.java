package com.honeybee.common.filter;

import com.honeybee.wrapper.InjectionAttackWrapper;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 防止XSS攻击、SQL注入的过滤器
 * 代码参考自：https://github.com/acslocum/InjectionAttackFilter
 */
@WebFilter(urlPatterns = "/*", filterName = "injectionAttackFilter")
public class InjectionAttackFilter extends OncePerRequestFilter {

    private static final String X_FRAME_HEADER = "X-FRAME-OPTIONS";
    private static final String X_FRAME_VALUE = "DENY";


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        InjectionAttackWrapper wrapperRequest = new InjectionAttackWrapper(request);
        filterClickJacking(response);
        filterChain.doFilter(wrapperRequest,response);
    }

    /**
     * 防止点击劫持
     * @param response HttpServletResponse
     */
    private void filterClickJacking(HttpServletResponse response) {
        if (!response.containsHeader(X_FRAME_HEADER)) {
            // 禁止在页面加载iframe页面，防止点击劫持
            response.addHeader(X_FRAME_HEADER,X_FRAME_VALUE);
        }
    }
}
