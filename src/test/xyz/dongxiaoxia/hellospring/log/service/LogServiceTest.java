package xyz.dongxiaoxia.hellospring.log.service;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.core.entity.Log;
import xyz.dongxiaoxia.hellospring.service.LogService;

import java.util.Date;

/**
 * Created by Administrator on 2015/11/7.
 */
public class LogServiceTest extends BasicTest {
    @Autowired
    private LogService logService;

    @Test
    public void logTest() {
        Log log = new Log();
       /* log.setUserId("456");
        log.setOperation("测试logService#log()");
        log.setContent("laal");*/
        log.setCreateDate(new Date());
        //logService.log(log);
    }
}
