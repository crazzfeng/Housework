package com.config.log.sys;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.util.Date;

/**
 * @author yufeng li
 * @title: MessageLogAspect
 * @description:
 * @date 2020/9/16 17:53
 */
@Aspect
@Component
public class SysLogAspect {


    @Pointcut(value = "@annotation(com.config.log.sys.SysLog)")
    public void pointcut() {
        System.out.println("------pointcut-----");
    }

    @Before("pointcut()")
    public void before() {
        System.out.println("------before-----");
    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
        System.out.println("------aroundstart-----");
        //执行方法前
        Date createTime = new Date();
        //参数值
        Object[] argValues = point.getArgs();

        String param = "";
        if (argValues != null) {
            for (Object o : argValues) {
                if (o instanceof String) {
                    param = (String) o;
                    break;
                }
            }
        }

        long time = System.currentTimeMillis();
        // 执行方法
        Object result = point.proceed();
        //执行方法后
        time = System.currentTimeMillis() - time;

        saveLog(point, time);
        System.out.println("------aroundend-----");
        return result;
    }


    @After("pointcut()")
    public void after() {
        System.out.println("------after-----");
    }

    private void saveLog(ProceedingJoinPoint joinPoint, long exectTime) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog aopLog = method.getAnnotation(SysLog.class);

        String type = aopLog.type();

    }
}
