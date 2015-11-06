package xyz.dongxiaoxia.hellospring;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2015/11/7.
 */
public class LogTest {
    static Logger logger = LoggerFactory.getLogger(LogTest.class);

    public static void main(String[] args) {
        // 记录error信息
        logger.error("[info message]");
        // 记录info，还可以传入参数
        //  logger.info("[info message]{},{},{},{}", "abc", false, 123,new LogTest());
        // 记录deubg信息
        logger.debug("[debug message]");
        // 记录trace信息
        logger.trace("[trace message]");
        System.out.println("hello world");
    }

    @Test
    public void log() {
        // 记录error信息
        logger.error("[info message]");
        // 记录info，还可以传入参数
        //  logger.info("[info message]{},{},{},{}", "abc", false, 123,new LogTest());
        // 记录deubg信息
        logger.debug("[debug message]");
        // 记录trace信息
        logger.trace("[trace message]");
        System.out.println("hello world");
    }

}
