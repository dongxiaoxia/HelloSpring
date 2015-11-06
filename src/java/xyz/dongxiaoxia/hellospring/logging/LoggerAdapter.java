package xyz.dongxiaoxia.hellospring.logging;

/**
 * Created by Administrator on 2015/11/7.
 */
public interface LoggerAdapter {
    public void info(String msg);

    public void info(Throwable ex);

    public void info(String msg, Throwable ex);

    public void info(String msg, Object... params);

    public void debug(String msg, Throwable ex);

    public void debug(String msg);

    public void debug(Throwable ex);

    public void debug(String msg, Object... params);

    public void error(String msg, Throwable ex);

    public void error(String msg);

    public void error(Throwable ex);

    public void error(String msg, Object... params);

    public void warn(String msg);

    public void warn(Throwable ex);

    public void warn(String msg, Throwable ex);

    public void warn(String msg, Object... params);
}


