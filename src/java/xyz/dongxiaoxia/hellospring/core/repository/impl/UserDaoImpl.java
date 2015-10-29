package xyz.dongxiaoxia.hellospring.core.repository.impl;

import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;

/**
 * Created by chenwendong on 2015/10/29.
 */
public class UserDaoImpl implements UserDao {
    @Override
    public User get(String id) {
        if (id.equals("123")) {
            User user = new User();
            user.setId("123");
            user.setName("东小侠");
            user.setPassword("123456");
            return user;
        }
        return null;
    }
}
