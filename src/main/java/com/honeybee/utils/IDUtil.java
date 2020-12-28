package com.honeybee.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

/**
 * ID生成工具
 */
public class IDUtil {

    // 日期格式化标准
    private static final String DATA_FORMAT = "%02d";

    // ID格式化标准
    private static final String ID_FORMAT = "%05d";

    // incr命令增长步长
    private static final int ID_INCREMENT = 1;

    private static final Logger logger = LoggerFactory.getLogger(IDUtil.class);

    /**
     * 获取当前时间到秒  2019年4月9日23点46分32秒---20190409234632
     * @return 格式化后的当前时间
     */
    private static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();

        // 获取当前时间并格式化
        int year = calendar.get(Calendar.YEAR);
        String month = String.format(DATA_FORMAT, calendar.get(Calendar.MONTH) + 1);
        String day = String.format(DATA_FORMAT, calendar.get(Calendar.DAY_OF_MONTH));
        String hour = String.format(DATA_FORMAT, calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.format(DATA_FORMAT, calendar.get(Calendar.MINUTE));
        String second = String.format(DATA_FORMAT, calendar.get(Calendar.SECOND));

        // 返回格式化后的字符串
        return year + month + day + hour + minute + second;
    }

    /**
     * 生成ID
     * @param key key
     * @return ID
     */
    public static String createId(String key) {
        // 获取RedisUtil实例
        RedisUtil redis = SpringContextUtil.getInstance().getBeanByClass(RedisUtil.class);

        try {
            // 指定key自增
            Long result = redis.incrBy(key, ID_INCREMENT);
            // 生成最终ID
            return getCurrentTime() + String.format(ID_FORMAT, result);
        } catch (Exception e) {
            logger.error("create id failed..", e);
            // TODO 补充异常情况下id生成策略
            return "";
        }

    }
}
