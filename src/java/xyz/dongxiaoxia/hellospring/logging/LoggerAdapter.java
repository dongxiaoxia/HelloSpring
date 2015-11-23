package xyz.dongxiaoxia.hellospring.logging;

/**
 * Created by Administrator on 2015/11/7.
 *
 * 日志类简单适配器接口
 */
public interface LoggerAdapter {
    void info(String msg);

    void info(Throwable ex);

    void info(String msg, Throwable ex);

    void info(String msg, Object... params);

    void debug(String msg, Throwable ex);

    void debug(String msg);

    void debug(Throwable ex);

    void debug(String msg, Object... params);

    void error(String msg, Throwable ex);

    void error(String msg);

    void error(Throwable ex);

    void error(String msg, Object... params);

    void warn(String msg);

    void warn(Throwable ex);

    void warn(String msg, Throwable ex);

    void warn(String msg, Object... params);
}


