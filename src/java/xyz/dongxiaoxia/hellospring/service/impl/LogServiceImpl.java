package xyz.dongxiaoxia.hellospring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.Log;
import xyz.dongxiaoxia.hellospring.core.repository.LogDao;
import xyz.dongxiaoxia.hellospring.service.LogService;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
@Transactional
@Component
public class LogServiceImpl implements LogService {

    @Autowired
    private LogDao logDao;

    @Override
    public Paging<Log> query(Log log, int pageStart, int pageSize) {
        Paging<Log> paging = logDao.$page(log, pageStart, pageSize);
        return paging;
    }

    @Override
    public void add(Log log) {
        logDao.$save(log);
    }

    @Override
    public void delete(String id) {
        logDao.$delete(id, Log.class);
    }

    @Override
    public void modify(Log log) {
        logDao.$update(log);
    }

    @Override
    public Log getById(String id) {
        return (Log) logDao.$get(id, Log.class);
    }

    @Override
    public List<Log> findAll(Log log) {
        return logDao.$query(null);
    }
}
