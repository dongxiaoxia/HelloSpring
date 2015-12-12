package xyz.dongxiaoxia.hellospring.core.repository;


import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by dongxiaoxia on 2015/11/17.
 * <p/>
 * 持久层的公用的增删改查接口，所有的dao接口都应该继承这个接口
 * <T> 表示传入的实体类
 */
public interface BaseDao<T> {

    int $save(T t);

    void $batchSave(List<T> list);

    void $delete(String id, Class<T> clazz);

    void $delete(String[] ids, Class<T> clazz);

    void $update(T t);

    void $batchUpdate(List<T> list);

    T $get(String id, Class<T> clazz);

    List<T> $query(T t);

    int $count(T t);

    Paging<T> $page(T t, int pageStart, int pageSize);

}
