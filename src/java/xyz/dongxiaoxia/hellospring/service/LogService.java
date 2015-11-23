package xyz.dongxiaoxia.hellospring.service;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.Log;
import xyz.dongxiaoxia.hellospring.util.PageView;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 * 日志记录业务逻辑
 */
public interface LogService {
    PageView query(PageView pageView, Log log);

    void add(Log log);

    void delete(String id);

    void modify(Log log);

    Log getById(String id);

    List<Log> findAll(Log log);
}
