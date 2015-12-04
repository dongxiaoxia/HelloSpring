package xyz.dongxiaoxia.hellospring.remoting_and_webservices.RMI;

/**
 * Created by Administrator on 2015/12/4.
 */
public interface UserDao {
    String getUser(String username) throws Exception;
}
