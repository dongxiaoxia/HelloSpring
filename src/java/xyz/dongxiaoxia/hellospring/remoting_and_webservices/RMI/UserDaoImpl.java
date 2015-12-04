package xyz.dongxiaoxia.hellospring.remoting_and_webservices.RMI;

/**
 * Created by Administrator on 2015/12/4.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public String getUser(String username) throws Exception {
        return "test" + username;
    }
}
