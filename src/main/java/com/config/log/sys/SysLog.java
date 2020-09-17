package com.config.log.sys;

import java.lang.annotation.*;

/**
 * @author yufeng li
 * @Title: SysLog
 * @Description:
 * @date 2020/9/16 17:49
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface SysLog {

    String type() default "";


}
