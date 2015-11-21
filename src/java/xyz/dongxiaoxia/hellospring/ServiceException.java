package xyz.dongxiaoxia.hellospring;

/**
 * Created by Administrator on 2015/11/21.
 */
public class ServiceException extends Exception {

    public ServiceException() {
        super();
    }

    public ServiceException(String msg) {
        super(msg);
    }
}
