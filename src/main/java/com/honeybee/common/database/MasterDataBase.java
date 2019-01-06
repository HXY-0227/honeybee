package com.honeybee.common.database;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

/**
 * 配置多数据源
 * @author HXY
 * @version 1.0
 */
@Configuration
@MapperScan(basePackages = MasterDataBase.PACKAGE, sqlSessionFactoryRef = "masterSqlSessionFactory")
public class MasterDataBase extends DataSource {
    static final String PACKAGE = "com.honeybee.dao";

    @Value("${mybatis.type-aliases-package}")
    private String typePackage;

    @Value("${mybatis.mapper-locations}")
    private String mapperLocation;

    /**
     * 配置数据源
     * @return
     */
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.master")
    @Bean(name="masterDataSource")
    public javax.sql.DataSource dataSource()throws Exception{
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置事务管理，如果使用到事务需要注入该 Bean，否则事务不会生效
     * 在需要的地方加上 @Transactional 注解即可
     * @return
     */
    @Bean(name = "masterTransactionManager")
    public DataSourceTransactionManager masterTransactionManager() throws Exception{
        return new DataSourceTransactionManager(dataSource());
    }

    /**
     * 配置 SqlSessionFactoryBean
     * @return
     * @throws Exception
     */
    @Bean(name = "masterSqlSessionFactory")
    public SqlSessionFactory masterSqlSessionFactory(@Qualifier("masterDataSource") javax.sql.DataSource masterDataSource)
            throws Exception {
        return sqlSessionFactory(masterDataSource, mapperLocation, typePackage);
    }
}
