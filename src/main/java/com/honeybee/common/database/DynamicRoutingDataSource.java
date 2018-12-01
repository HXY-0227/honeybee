package com.honeybee.common.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class DynamicRoutingDataSource extends AbstractRoutingDataSource {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 在访问数据库时会调用，根据Key获取数据源的信息
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        logger.info("Current DataSource is [{}]",DynamicDataSourceContextHolder.getDataSourceKey());
        return DynamicDataSourceContextHolder.getDataSourceKey();
    }
}
