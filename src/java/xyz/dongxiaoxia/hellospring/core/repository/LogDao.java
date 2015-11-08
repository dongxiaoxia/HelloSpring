package xyz.dongxiaoxia.hellospring.core.repository;

import xyz.dongxiaoxia.hellospring.core.entity.Log;

import java.util.List;

/**
 * Created by Administrator on 2015/11/7.
 */
public interface LogDao {
    int insert(Log log);

    int delete(String id);

    int update(Log log);

    Log get(String id);

    List<Log> list();

}
