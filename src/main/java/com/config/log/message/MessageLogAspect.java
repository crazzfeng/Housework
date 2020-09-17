package com.config.log.message;

import com.config.log.sys.SysLog;
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
public class MessageLogAspect {

    @Pointcut(value = "@annotation(com.config.log.message.MessageLog)")
    public void pointcut() {
    }

    @Before("pointcut()")
    public void before() {

    }

    @Around("pointcut()")
    public Object around(ProceedingJoinPoint point) throws Throwable {
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

        saveMessageLog(point,time);
        return result;

    }


    @After("pointcut()")
    public void after() {

    }

    private void saveMessageLog(ProceedingJoinPoint joinPoint, long exectTime) {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        SysLog aopLog = method.getAnnotation(SysLog.class);

        String type = aopLog.type();

    }
}
