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
    void add(T t);

    void delete();

    void modify(T t);

    T getById(String id);

    List<T> queryAll(T t);

    /**
     * 返回分页后的数据
     *
     * @param pageView
     * @param t
     * @return
     */
    List<T> query(PageView pageView, T t);
}
