package com.honeybee.wrapper;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * 包装HttpServletRequest，实现防止XSS攻击、SQL注入等。
 * 代码来自https://github.com/acslocum/InjectionAttackFilter
 */
public class InjectionAttackWrapper extends HttpServletRequestWrapper {

    //js中的事件
    private static final String EVENTS = "((?i)onload|onunload|onchange|onsubmit|onreset"
            + "|onselect|onblur|onfocus|onkeydown|onkeypress|onkeyup"
            + "|onclick|ondblclick|onmousedown|onmousemove|onmouseout|onmouseover|onmouseup)";

    //html标记符号 %3C--<  %3E-->
    private static final String XSS_HTML_TAG = "(%3C)|(%3E)|[<>]+";

    //XSS注入特殊字符  %22--" %20--" " %3D--= \\s--空格
    private static final String XSS_INJECTION = "((%22%20)|(%22\\s)|('%22)|(%22\\+))\\w.*|(\\s|%20)"
            + EVENTS + ".*|(%3D)|(%7C)";

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
     * @param name key
     * @return 过滤后的value
     */
    @Override
    public String getParameter(String name) {
        //调用父类方法，获取原始的value
        String parameterValue = super.getParameter(name);
        return filterParamString(parameterValue);
    }

    /**
     * 重写getParameterMap方法
     * @return 过滤后的value
     */
    @Override
    public Map<String, String[]> getParameterMap() {
        //调用父类方法，获取原始的value
        Map<String, String[]> parameterMap =  super.getParameterMap();
        Map<String, String[]> filteredMap = new HashMap<>(parameterMap.size());

        //遍历原始value，将value取出进行过滤，entrySet遍历效率高于keySet
        Set<Map.Entry<String, String[]>> entrySet = parameterMap.entrySet();
        Iterator<Map.Entry<String, String[]>> it = entrySet.iterator();
        while (it.hasNext()) {
            Map.Entry<String, String[]> entry = it.next();
            String[] parameterValues = entry.getValue();
            String[] filteredValue = filterStringArray(parameterValues);
            filteredMap.put(entry.getKey(),filteredValue);
        }

        return filteredMap;
    }

    /**
     * 重写getParameterValues方法
     * @param name key
     * @return 过滤后的value
     */
    @Override
    public String[] getParameterValues(String name) {
        //调用父类方法，获取原始的value
        String[] parameterValues = super.getParameterValues(name);
        String[] filteredValues = new String[parameterValues.length];
        //遍历原始value，将value取出进行过滤
        for (int i = 0; i < parameterValues.length; i++) {
            filteredValues[i] = filterParamString(parameterValues[i]);
        }
        return filteredValues;
    }

    /**
     * 重写getCookies方法
     * @return 过滤后的cookie
     */
    @Override
    public Cookie[] getCookies() {
        Cookie[] cookies = super.getCookies();
        if (null != cookies) {
            for (int i = 0; i < cookies.length; i++) {
                Cookie cookie = cookies[i];
                cookie.setValue(filterParamString(cookie.getValue()));
            }
        }

        return cookies;
    }

    /**
     * 过滤getParameterMap获取到的value数组
     * @param parameterValue
     * @return 过滤后的getParameterMap获取到的value数组
     */
    protected String[] filterStringArray(String[] parameterValue) {

        String[] filteredArray = new String[parameterValue.length];
        for (int i = 0; i < parameterValue.length; i++) {
            filteredArray[i] = filterParamString(parameterValue[i]);
        }
        return filteredArray;
    }

    /**
     * 过滤可能引起XSS攻击和sql注入的特殊字符
     * @param value 过滤前的参数
     * @return 过滤后的参数
     */
    protected String filterParamString(String value) {

        if (null == value) {
            return null;
        }

        //过滤可能引起XSS攻击的特殊字符
        value = value.replaceAll(XSS_REGEX, "");
        //过滤可能引起sql注入的特殊字符
        value = value.replaceAll(SQL_REGEX, "");

        return value;
    }

    @Override
    public String getQueryString() {
        return filterParamString(super.getQueryString());
    }


}
