package xyz.dongxiaoxia.hellospring.logging;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Administrator on 2015/11/7.
 * A simple logger's adapter of <code>java.util.logging.Logger</code>
 *
 * @see java.util.logging.Logger
 */
public class LoggerJavaAdapter implements LoggerAdapter {
    private Logger logger;

    public LoggerJavaAdapter(Class clazz) {
        logger = Logger.getLogger(clazz != null ? clazz.getName() : " ");
    }

    @Override
    public void debug(String msg, Throwable ex) {
        logger.log(Level.FINEST, msg, ex);
    }

    @Override
    public void debug(String msg) {
        logger.log(Level.FINEST, msg);
    }

    @Override
    public void debug(Throwable ex) {
        if (ex == null) {
            logger.log(Level.FINEST, null);
        } else {
            logger.log(Level.FINEST, ex.getMessage(), ex);
        }
    }

    @Override
    public void debug(String msg, Object... params) {
        logger.log(Level.FINEST, msg, params);
    }

    @Override
    public void error(String msg, Throwable ex) {
        logger.log(Level.SEVERE, msg, ex);
    }

    @Override
    public void error(String msg) {
        logger.log(Level.SEVERE, msg);
    }

    @Override
    public void error(Throwable ex) {
        if (ex == null) {
            logger.log(Level.SEVERE, null);
        } else {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }

    @Override
    public void error(String msg, Object... params) {
        logger.log(Level.SEVERE, msg, params);
    }

    @Override
    public void info(String msg, Throwable ex) {
        logger.log(Level.INFO, msg, ex);
    }

    @Override
    public void info(String msg) {
        logger.log(Level.INFO, msg);
    }

    @Override
    public void info(Throwable ex) {
        if (ex == null) {
            logger.log(Level.INFO, null);
        } else {
            logger.log(Level.INFO, ex.getMessage(), ex);
        }
    }

    @Override
    public void info(String msg, Object... params) {
        logger.log(Level.INFO, msg, params);
    }

    @Override
    public void warn(String msg, Throwable ex) {
        logger.log(Level.WARNING, msg, ex);
    }

    @Override
    public void warn(String msg) {
        logger.log(Level.WARNING, msg);
    }

    @Override
    public void warn(Throwable ex) {
        if (ex == null) {
            logger.log(Level.WARNING, null);
        } else {
            logger.log(Level.WARNING, ex.getMessage(), ex);
        }
    }

    @Override
    public void warn(String msg, Object... params) {
        logger.log(Level.WARNING, msg, params);
    }
}
