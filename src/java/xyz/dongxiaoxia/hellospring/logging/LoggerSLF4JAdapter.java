package xyz.dongxiaoxia.hellospring.logging;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by Administrator on 2015/11/7.
 *
 * sla4j日志适配器实现类
 */
public class LoggerSLF4JAdapter implements LoggerAdapter {
    private Logger logger;

    public LoggerSLF4JAdapter(Class clazz) {
        logger = LoggerFactory.getLogger(clazz != null ? clazz.getName() : " ");
    }

    @Override
    public void info(String msg) {
        if (logger.isInfoEnabled()) {
            logger.info(msg);
        }
    }

    @Override
    public void info(Throwable ex) {
        if (logger.isInfoEnabled()) {
            logger.info(ex.getMessage(), ex);
        }
    }

    @Override
    public void info(String msg, Throwable ex) {
        if (logger.isInfoEnabled()) {
            logger.info(msg, ex);
        }
    }

    @Override
    public void info(String msg, Object... params) {
        if (logger.isInfoEnabled()) {
            logger.info(msg, params);
        }
    }

    @Override
    public void debug(String msg, Throwable ex) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg, ex);
        }
    }

    @Override
    public void debug(String msg) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg);
        }
    }

    @Override
    public void debug(Throwable ex) {
        if (logger.isDebugEnabled()) {
            logger.debug(ex.getMessage(), ex);
        }
    }

    @Override
    public void debug(String msg, Object... params) {
        if (logger.isDebugEnabled()) {
            logger.debug(msg, params);
        }
    }

    @Override
    public void error(String msg, Throwable ex) {
        if (logger.isErrorEnabled()) {
            logger.error(msg, ex);
        }
    }

    @Override
    public void error(String msg) {
        if (logger.isErrorEnabled()) {
            logger.error(msg);
        }
    }

    @Override
    public void error(Throwable ex) {
        if (logger.isErrorEnabled()) {
            logger.error(ex.getMessage(), ex);
        }
    }

    @Override
    public void error(String msg, Object... params) {
        if (logger.isErrorEnabled()) {
            logger.error(msg, params);
        }
    }

    @Override
    public void warn(String msg) {
        if (logger.isWarnEnabled()) {
            logger.warn(msg);
        }
    }

    @Override
    public void warn(Throwable ex) {
        if (logger.isWarnEnabled()) {
            logger.warn(ex.getMessage(), ex);
        }
    }

    @Override
    public void warn(String msg, Throwable ex) {
        if (logger.isWarnEnabled()) {
            logger.warn(msg, ex);
        }
    }

    @Override
    public void warn(String msg, Object... params) {
        if (logger.isWarnEnabled()) {
            logger.warn(msg, params);
        }
    }
}
