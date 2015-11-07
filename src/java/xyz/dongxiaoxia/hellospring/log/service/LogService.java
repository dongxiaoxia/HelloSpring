package xyz.dongxiaoxia.hellospring.log.service;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.Log;
import xyz.dongxiaoxia.hellospring.core.repository.LogDao;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/7.
 * 日志记录业务逻辑
 */
@Component
@Transactional
public class LogService {

    @Resource
    private LogDao logDao;

    public void save(Log log) {
        logDao.save(log);
    }

    public void delete(String id) {
        logDao.delete(id);
    }

    public void update(Log log) {
        logDao.update(log);
    }

    public Log get(String id) {
        return logDao.get(id);
    }
}
