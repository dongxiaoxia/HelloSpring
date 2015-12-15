package xyz.dongxiaoxia.hellospring.service;

import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/12/15.
 * 服务层基类，所有的服务层接口都应该继承这个接口
 */
public interface BaseService<T> {

    int save(T t);

    void delete(String id);

    void update(T t);

    T get(String id);

    List<T> list(T t);

    Paging<T> page(T t, int pageStart, int pageSize);
}
