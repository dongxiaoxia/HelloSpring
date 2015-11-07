package xyz.dongxiaoxia.hellospring.logging;

import org.junit.Test;

/**
 * Created by Administrator on 2015/11/7.
 */
public class LoggerJavaAdapterTest {

    static LoggerAdapter logger = LoggerAdapterFactory.getLoggerAdapter(LoggerJavaAdapter.class);

    @Test
    public void test() {
        logger.info("test info(String msg)");
        logger.info(new Throwable("test info(Throwable ex)"));
        logger.info("test info(Throwable ex)", new Throwable());

        logger.warn("tests warn()");
    }
}
