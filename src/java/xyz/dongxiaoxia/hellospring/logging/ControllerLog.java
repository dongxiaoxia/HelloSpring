package xyz.dongxiaoxia.hellospring.logging;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.Documented;

/**
 * Created by Administrator on 2015/11/7.
 * <p/>
 * Controller层日志注解
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {

    /**
     * 日志记录是的哪个模块，比如：UserController，
     *
     * @return
     */
    String module() default "";

    /**
     * 要执行的操作类型比如：add操作、delete操作
     */
    String operationType() default "";

    /**
     * 要执行的具体操作比如：添加用户
     */
    String operationName() default "";
}
