package com.honeybee.utils;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;

/**
 * ID生成工具
 */
public class IDUtil {

    @Autowired
    private RedisUtil redis;

    // 日期格式化标准
    private static final String DATA_FORMAT = "%02d";

    // ID格式化标准
    private static final String ID_FORMAT = "%05d";

    /**
     * 获取当前时间到秒  2019年4月9日23点46分32秒---20190409234632
     * @return 格式化后的当前时间
     */
    private static String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();

        int year = calendar.get(Calendar.YEAR);
        String month = String.format(DATA_FORMAT, calendar.get(Calendar.MONTH) + 1);
        String day = String.format(DATA_FORMAT, calendar.get(Calendar.DAY_OF_MONTH));
        String hour = String.format(DATA_FORMAT, calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.format(DATA_FORMAT, calendar.get(Calendar.MINUTE));
        String second = String.format(DATA_FORMAT, calendar.get(Calendar.SECOND));

        return year + month + day + hour + minute + second;
    }

    /**
     * 生成ID
     * @param key key
     * @return ID
     */
    public Long createId(String key) {

        Long increment = redis.incrBy(key, 1);
        String id = getCurrentTime() + String.format(ID_FORMAT, increment);
        return Long.valueOf(id);
    }
}
