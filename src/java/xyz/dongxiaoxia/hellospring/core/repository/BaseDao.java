package xyz.dongxiaoxia.hellospring.core.repository;


import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 * <p/>
 * 持久层的公用的增删改查接口
 * <T> 表示传入的实体类
 */
public interface BaseDao<T> {

    int $save(T t);

    void $delete(String id, Class clazz);

    void $update(T t);

    T $get(String id, Class<T> clazz);

    List<T> $query(T t);

    Paging<T> page(T t, int pageStart, int pageSize);

}
