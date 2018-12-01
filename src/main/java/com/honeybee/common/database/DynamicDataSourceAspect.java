package com.honeybee.common.database;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * 使用AOP拦截特定的注解去动态的切换数据源
 */
@Aspect
@Component
public class DynamicDataSourceAspect {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 切换DataSource  前置方法，在连接点之前执行
     * @param point
     * @param targetDataSource
     *
     */
    @Before("@annotation(targetDataSource))")
    public void switchDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        if (!DynamicDataSourceContextHolder.containSourceKey(targetDataSource.value())){
            logger.error("DataSource [{}] doesn't exist, use default DataSource [{}]", targetDataSource.value());
        }else {
            DynamicDataSourceContextHolder.setDataSourcesKye(targetDataSource.value());
            logger.info("Switch DataSource to [{}] in Method [{}]",
                    DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
        }
    }

    /**
     * 设置数据源为默认数据源  后置方法在连接点方法完成之后执行
     * @param point 切面插入应用程序的地方，pointcut:切点  如果JoinPoint相当于数据中的记录，切点相当于查询条件
     * @param targetDataSource
     */
    @After("@annotation(targetDataSource))")
    public void restoreDataSource(JoinPoint point, TargetDataSource targetDataSource) {
        //JoinPoint
        DynamicDataSourceContextHolder.clearDataSourceKey();
        logger.info("Restore DataSource to [{}] in Method [{}]",
                //point.getSignature() 获取被增强的方法的信息  获取到的信息：修饰符+包名+类名+方法名
                //point.getargs() 获取带参数的方法参数
                //point.getTargs() 获取他们的目标对象的信息
                DynamicDataSourceContextHolder.getDataSourceKey(), point.getSignature());
    }

}
