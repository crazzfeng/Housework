package com.config.log.message;

import java.lang.annotation.*;

/**
 * @author yufeng li
 * @title: MessageLog
 * @description:
 * @date 2020/9/16 17:48
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageLog {

    String type() default "";
}
