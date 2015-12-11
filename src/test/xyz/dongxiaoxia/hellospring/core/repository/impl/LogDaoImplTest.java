package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.core.entity.Log;
import xyz.dongxiaoxia.hellospring.core.repository.LogDao;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2015/11/7.
 */
public class LogDaoImplTest extends BasicTest {

    @Autowired
    private LogDao logDao;

    @Test
    public void insertTest() {
        Log log = new Log();
        log.setDescription("测试");
        log.setMethod("insertTest");
        log.setLogType(1111L);
        log.setRequestIp("222.222.222.222");
        log.setExceptionCode("01");
        log.setExceptionDetail("test");
        log.setParams("123");
        log.setCreateBy("dong");
        log.setCreateDate(new Date());
        Assert.assertEquals(1, logDao.$save(log));
    }

    @Test
    public void updateTest() {
        Log log = new Log();
        log.setId("37");
        log.setDescription("测试");
        log.setMethod("insertTest");
        log.setLogType(1111L);
        log.setRequestIp("222.222.222.222");
        log.setExceptionCode("01");
        log.setExceptionDetail("test");
        log.setParams("123");
        log.setCreateBy("dong");
        log.setCreateDate(new Date());
        // Assert.assertEquals(1, logDao.$update(log));
    }

    @Test
    public void getTest() {
        // Assert.assertEquals("insertTest", logDao.get("40").getMethod());
    }

    @Test
    public void listTest() {
        Assert.assertEquals(37, logDao.$query(null).size());
    }
}
