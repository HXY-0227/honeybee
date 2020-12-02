package com.honeybee.common.aop;

import com.honeybee.common.annotation.Cache;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.DefaultParameterNameDiscoverer;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * 缓存的切面
 *
 * @author : HXY
 * @date : 2020-12-02 23:47
 **/
@Aspect
@Component
@Slf4j
public class CacheAspect {

    private static final String PREFIX_SEPARATOR = ":";

    @Resource
    private RedisTemplate redisTemplate;

    @Pointcut("@annotation(com.honeybee.common.annotation.Cache)")
    public void cachePointcut() {

    }

    @Around("cachePointcut()")
    public Object doCache(ProceedingJoinPoint joinPoint) {
        Object value = null;
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        try {
            Method method = joinPoint.getTarget().getClass().getMethod(signature.getName(), signature.getMethod().getParameterTypes());
            Cache annotation = method.getAnnotation(Cache.class);

            String prefix = annotation.prefix();
            String keyEl = annotation.key();
            int expireTime = annotation.expireTime();

            String key = prefix + PREFIX_SEPARATOR + parseEl(joinPoint, method, keyEl);

            // 查询缓存
            value = redisTemplate.opsForValue().get(key);
            if (null != value) {
                log.info("query {} from cache successfully...", key);
                return value;
            }

            // 没有命中缓存，则执行原来的方法
            value = joinPoint.proceed();

            // 更新缓存
            redisTemplate.opsForValue().set(key, value, expireTime, TimeUnit.SECONDS);
        } catch (Throwable throwable) {
            log.error("execute origin method failed...", throwable);
        }

        return value;
    }

    private String parseEl(ProceedingJoinPoint joinPoint, Method method, String keyEl) {
        StandardEvaluationContext context = new StandardEvaluationContext();

        SpelExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression(keyEl);

        Object[] args = joinPoint.getArgs();
        DefaultParameterNameDiscoverer discoverer = new DefaultParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);
        for (int i = 0; i < parameterNames.length; i++) {
            context.setVariable(parameterNames[i], args[i].toString());
        }
        return expression.getValue(context).toString();
    }
}
