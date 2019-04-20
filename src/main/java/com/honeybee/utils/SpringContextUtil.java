package com.honeybee.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * 获取spring上下文工具类
 * @author HXY
 * @version 1.0
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {

    //spring应用上下文环境
    private static ApplicationContext applicationContext;

    //实例化对象
    private static volatile SpringContextUtil instance = null;

    /**
     * 实现ApplicationContextAware接口的回调方法，设置上下文环境
     * @param applicationContext
     * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    /**
     * 双重锁校验的单例模式保证创建唯一实例
     * @return SpringContextUtil实例
     */
    public static SpringContextUtil getInstance() {
        if (null == instance) {
            synchronized (SpringContextUtil.class) {
                if (null == instance) {
                    instance = new SpringContextUtil();
                }
            }
        }
        return instance;
    }

    /**
     * 获取spring上下文
     * @return
     */
    public ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 根据名称获取对象
     * @param name 对象注册在spring中的名称
     * @return
     */
    public Object getBeanByName(String name) {
        return getApplicationContext().getBean(name);
    }

    /**
     * 根据类型获取对象
     * @param clazz 特定类型
     * @param <T>
     * @return
     */
    public <T> T getBeanByClass(Class<T> clazz) {
        return getApplicationContext().getBean(clazz);
    }
}
