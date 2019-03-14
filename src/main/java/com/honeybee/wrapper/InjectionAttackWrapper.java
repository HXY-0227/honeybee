package com.honeybee.wrapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;

public class InjectionAttackWrapper extends HttpServletRequestWrapper {

    //js中的事件
    private static final String EVENTS = "((?i)onload|onunload|onchange|onsubmit|onreset" + "|onselect|onblur|onfocus|onkeydown|onkeypress|onkeyup"
            + "|onclick|ondblclick|onmousedown|onmousemove|onmouseout|onmouseover|onmouseup)";

    //html标记符号 %3C--<  %3E-->
    private static final String XSS_HTML_TAG = "(%3C)|(%3E)|[<>]+";

    //XSS注入特殊字符  %22--" %20--" " %3D--= \\s--空格
    private static final String XSS_INJECTION = "((%22%20)|(%22\\s)|('%22)|(%22\\+))\\w.*|(\\s|%20)" + EVENTS + ".*|(%3D)|(%7C)";

    //XSS注入正则表达式
    private static final String XSS_REGEX = XSS_HTML_TAG + "|" + XSS_INJECTION;

    //sql正则表达式  --会注释掉sql语句  %7C--|
    private static final String SQL_REGEX = "('.+--)|(--)|(\\|)|(%7C)";

    /**
     * Constructs a request object wrapping the given request.
     *
     * @param request The request to wrap
     * @throws IllegalArgumentException if the request is null
     */
    public InjectionAttackWrapper(HttpServletRequest request) {
        super(request);
    }

    /**
     * 重写getParameter方法
     * @param name 过滤前的参数
     * @return 过滤后的参数
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(name);
        return filterParamString(value);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return super.getParameterMap();
    }

    @Override
    public String[] getParameterValues(String name) {
        return super.getParameterValues(name);
    }

    /**
     * 过滤可能引起XSS攻击和sql注入的特殊字符
     * @param value 过滤前的参数
     * @return 过滤后的参数
     */
    private String filterParamString(String value) {

        if (null == value) {
            return null;
        }

        //过滤可能引起XSS攻击的特殊字符
        value = value.replaceAll(XSS_REGEX, "");
        //过滤可能引起sql注入的特殊字符
        value = value.replaceAll(SQL_REGEX, "");

        return value;
    }
}
