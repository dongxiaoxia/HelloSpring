package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.Log;

/**
 * Created by Administrator on 2015/11/7.
 */
public interface LogDao {
    void save(Log log);

    void delete(String id);

    void update(Log log);

    Log get(String id);

}
