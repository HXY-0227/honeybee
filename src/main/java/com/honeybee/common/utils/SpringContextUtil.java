package com.honeybee.common.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

@Component
public class SpringContextUtil implements ApplicationContextAware {

    //spring应用上下文环境
    private static ApplicationContext applicationContext;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        if (SpringContextUtil.applicationContext == null) {
            SpringContextUtil.applicationContext = applicationContext;
        }
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据名称获取对象
     * @param name 对象注册在spring中的名称
     * @return
     */
    public static Object getBeanByName(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 根据类型获取对象
     * @param clazz 特定类型
     * @param <T>
     * @return
     */
    public static <T> T getBeanByClass(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
}
