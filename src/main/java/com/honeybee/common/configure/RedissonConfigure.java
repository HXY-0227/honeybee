package com.honeybee.common.configure;

import org.redisson.Redisson;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * redisson配置类
 *
 * @author HXY
 * @since 2020-5-08
 */
@Configuration
public class RedissonConfigure {

    @Value("${redisson.address}")
    private String address;

    @Bean
    public Redisson redission() {
        Config config = new Config();
        config.useSingleServer().setAddress(address).setDatabase(0);
        return (Redisson) Redisson.create(config);
    }
}
