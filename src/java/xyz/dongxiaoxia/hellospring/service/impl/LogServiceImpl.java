package xyz.dongxiaoxia.hellospring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.Log;
import xyz.dongxiaoxia.hellospring.core.repository.LogDao;
import xyz.dongxiaoxia.hellospring.service.LogService;
import xyz.dongxiaoxia.hellospring.util.PageView;

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
    public PageView query(PageView pageView, Log log) {
        List<Log> list = logDao.query(pageView, log);
        pageView.setRecords(list);
        return pageView;
    }

    @Override
    public void add(Log log) {
        logDao.insert(log);
    }

    @Override
    public void delete(String id) {
        logDao.delete(id);
    }

    @Override
    public void modify(Log log) {
        logDao.update(log);
    }

    @Override
    public Log getById(String id) {
        return logDao.get(id);
    }

    @Override
    public List<Log> findAll(Log log) {
        return logDao.list();
    }
}
