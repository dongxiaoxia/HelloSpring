package xyz.dongxiaoxia.hellospring.core.repository.persistence.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2015/12/6.
 * <p/>
 * 实体类表名注解
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Entity {
    /**
     * 表名
     *
     * @return
     */
    String value() default "";
}
