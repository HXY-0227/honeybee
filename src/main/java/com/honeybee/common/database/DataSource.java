package com.honeybee.common.database;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * 定义获取SqlSessionFactory对象的父类，用于被继承
 * @author HXY
 * @version 1.0
 */
public class DataSource {
    /**
     * 配置 SqlSessionFactoryBean
     * @return
     * @throws Exception
     */
    public SqlSessionFactory sqlSessionFactory(javax.sql.DataSource dataSource, String mapperLocation, String typePackage)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(mapperLocation));
        sessionFactory.setTypeAliasesPackage(typePackage);

        return sessionFactory.getObject();
    }
}
