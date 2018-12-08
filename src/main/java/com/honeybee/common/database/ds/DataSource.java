package com.honeybee.common.database.ds;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

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
