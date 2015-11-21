package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.util.PageView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/17.
 * <p/>
 * 持久层的公用的增删改查接口
 * <T> 表示传入的实体类
 */
public interface BaseDao<T> {

    int insert(T t);

    int delete(String id);

    int update(T t);

    T get(String id);

    List<T> list(T t);

    List<T> page(PageView pageView, T t);

}
