package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.core.entity.Log;
import xyz.dongxiaoxia.hellospring.core.repository.LogDao;

import java.util.Date;

/**
 * Created by Administrator on 2015/11/7.
 */
public class LogDaoImplTest extends BasicTest {

    @Autowired
    private LogDao logDao;

    @Test
    public void saveTest() {
        Log log = new Log();
//        log.setContent("测试保存");
//        log.setOperation("lalala啦啦啦");
//        log.setUserId("123");
//        log.setCreateDate(new Date());
        logDao.save(log);
    }
}
