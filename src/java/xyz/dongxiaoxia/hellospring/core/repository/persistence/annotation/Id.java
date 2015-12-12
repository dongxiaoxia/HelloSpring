package xyz.dongxiaoxia.hellospring.core.repository.persistence.annotation;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2015/12/8.
 * <p/>
 * 实体类主键注解
 */
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Id {

}
