package xyz.dongxiaoxia.hellospring.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;
import xyz.dongxiaoxia.hellospring.core.repository.impl.UserDaoImpl;

import javax.annotation.Resource;

/**
 * Created by chenwendong on 2015/10/29.
 */
@Component
@Transactional
public class UserService {
    @Resource
    private UserDao userDao;

    public User getUser(String id) {
        return userDao.get(id);
    }

    public int getUserCount() {
        return userDao.getCount();
    }
}
