package xyz.dongxiaoxia.hellospring.aop;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2015/11/7.
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyLog {
    /**
     * 要执行的操作类型比如：add操作
     */
    public String operationType() default "";

    /**
     * 要执行的具体操作比如：添加用户
     */
    public String operationName() default "";
}
