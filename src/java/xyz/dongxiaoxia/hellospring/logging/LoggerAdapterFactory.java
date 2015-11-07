package xyz.dongxiaoxia.hellospring.logging;

/**
 * Created by Administrator on 2015/11/7.
 * The factory of implements logger adapter.if is in need,you can rewrite it.
 */
public class LoggerAdapterFactory {
    public static LoggerAdapter getLoggerAdapter(Class clazz) {
        //TODO according to specific circumstance
        //such as 'LoggerJava'
        //return new LoggerJavaAdapter(clazz);
        return new LoggerSLF4JAdapter(clazz);
    }
}
