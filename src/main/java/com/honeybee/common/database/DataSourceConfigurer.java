package com.honeybee.common.database;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DataSourceConfigurer {

    /**
     * 主数据源
     * @return
     */
    @Bean(name = "master")
    @Primary
    @ConfigurationProperties(prefix = "")
    public DataSource master() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 备数据源
     * @return
     */
    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "")
    public DataSource slave() {
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 动态数据源
     * @return
     */
    @Bean(name = "dynamicDataSource")
    public DataSource dynamicDataSource() {
        DynamicRoutingDataSource dynamicRoutingDataSource = new DynamicRoutingDataSource();
        Map<Object,Object> dataSourceMap = new HashMap<>(2);
        dataSourceMap.put("master", master());
        dataSourceMap.put("slave", slave());

        // 将 master 数据源作为默认指定的数据源
        dynamicRoutingDataSource.setDefaultTargetDataSource(master());
        // 将 master 和 slave 数据源作为指定的数据源
        dynamicRoutingDataSource.setTargetDataSources(dataSourceMap);
        // 将数据源的 key 放到数据源上下文的 key 集合中，用于切换时判断数据源是否有效
        DynamicDataSourceContextHolder.dataSourcesKyes.addAll(dataSourceMap.keySet());
        return dynamicRoutingDataSource;
    }

    /**
     * 配置 SqlSessionFactoryBean
     * @return
     * @throws Exception
     */
    @Bean(name = "sqlSessionFactoryBean")
    @ConfigurationProperties(prefix = "")
    public SqlSessionFactoryBean sqlSessionFactoryBean() throws Exception{
        final SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource());
        sqlSessionFactoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(""));
        sqlSessionFactoryBean.setTypeAliasesPackage("");
        return sqlSessionFactoryBean;

    }

    /**
     * 配置事务管理，如果使用到事务需要注入该 Bean，否则事务不会生效
     * 在需要的地方加上 @Transactional 注解即可
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dynamicDataSource());
    }
}
