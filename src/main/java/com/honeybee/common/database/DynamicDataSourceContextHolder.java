package com.honeybee.common.database;

import java.util.ArrayList;
import java.util.List;

/**
 * 数据源上下文配置
 */
public class DynamicDataSourceContextHolder {

    private static ThreadLocal<String> contextHolder = new ThreadLocal<String>() {

        /**
         * 将 master 数据源的 key 作为默认数据源的 key
         * @return
         */
        @Override
        protected String initialValue() {
            return "master";
        }
    };


    /**
     * 数据源的 key 集合，用于切换时判断数据源是否存在
     */
    public static List<Object> dataSourceKeys = new ArrayList<>();

    /**
     * 设置数据源的key
     * @param key
     */
    public static void setDataSourceKey(String key) {
        contextHolder.set(key);
    }

    /**
     * 获取数据源的key
     * @return
     */
    public static String getDataSourceKey() {
        return contextHolder.get();
    }

    /**
     * 设置为默认的DataSource
     */
    public static void clearDataSourceKey() {
        contextHolder.remove();
    }

    /**
     * 判断该DataSource是否在当前的DataSource列表中
     * @param key
     * @return
     */
    public static boolean containDataSourceKey(String key) {
        return dataSourceKeys.contains(key);
    }

}
