package xyz.dongxiaoxia.hellospring.service.impl;

import xyz.dongxiaoxia.hellospring.service.BaseService;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/12/15.
 */
public abstract class BaseServiceImpl<T> implements BaseService<T> {


    @Override
    public int save(T t) {
        return 0;
    }

    @Override
    public void delete(String id) {

    }

    @Override
    public void update(T t) {

    }

    @Override
    public T get(String id) {
        return null;
    }

    @Override
    public List<T> list(T t) {
        return null;
    }

    @Override
    public Paging<T> page(T t, int pageStart, int pageSize) {
        return null;
    }
}
