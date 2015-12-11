package xyz.dongxiaoxia.hellospring.core.repository.persistence;

/**
 * Created by dongxiaoxia on 2015/12/12.
 * <p/>
 * 自定义持久层运行时错误
 */
public class PersistenceException extends RuntimeException {

    public PersistenceException() {
        super();
    }

    public PersistenceException(String msg) {
        super(msg);
    }
}
