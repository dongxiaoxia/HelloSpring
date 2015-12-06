package xyz.dongxiaoxia.hellospring.util.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2015/12/7.
 * <p/>
 * 实体类属性--列明映射注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Column {
    String value() default "";
}
