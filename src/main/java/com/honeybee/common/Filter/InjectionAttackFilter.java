package com.honeybee.common.Filter;

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

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

    }
}
