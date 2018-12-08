package com.honeybee.common.database.ds;

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

@Configuration
@MapperScan(basePackages = SlaveDataSource.PACKAGE, sqlSessionFactoryRef = "cmSqlSessionFactory")
public class SlaveDataSource extends DataSource{
    static final String PACKAGE = "com.honeybee.dao";

    @Value("${mybatis.type-aliases-package}")
    private String typePackage;

    @Value("${mybatis.mapperLocations.cm}")
    private String mapperLocation;

    /**
     * 配置数据源
     * @return
     */
    @Primary
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    @Bean(name = "slaveDataSource")
    public javax.sql.DataSource dataSource(){
        return DruidDataSourceBuilder.create().build();
    }

    /**
     * 配置事务管理，如果使用到事务需要注入该 Bean，否则事务不会生效
     * 在需要的地方加上 @Transactional 注解即可
     * @return
     */
    @Bean(name = "slaveTransactionManager")
    @Primary
    public DataSourceTransactionManager slaveTransactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    /**
     * 配置 SqlSessionFactoryBean
     * @return
     * @throws Exception
     */
    @Bean(name = "slaveSqlSessionFactory")
    @Primary
    public SqlSessionFactory slaveSqlSessionFactory(@Qualifier("slaveDataSource") javax.sql.DataSource slaveDataSource)
            throws Exception {
        return sqlSessionFactory(slaveDataSource, mapperLocation, typePackage);
    }
}
