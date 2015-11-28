package xyz.dongxiaoxia.hellospring.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;
import xyz.dongxiaoxia.hellospring.service.UserService;
import xyz.dongxiaoxia.hellospring.util.Common;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.util.List;

/**
 * Created by Administrator on 2015/11/19.
 */
@Component
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public void add(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user must not be null");
        }
        if (Common.isEmpty(user.getUsername())) {
            throw new IllegalArgumentException("username must not be null");
        }
        if (querySingleUser(user.getUsername()) != null) {
            throw new IllegalArgumentException("username is exist");
        }
        if (Common.isEmpty(user.getPassword())) {
            throw new IllegalArgumentException("password must not be null");
        }
        if (Common.isEmpty(user.getStatus())) {
            throw new IllegalArgumentException("status must not be null");
        }
        userDao.insert(user);
    }

    @Override
    public void delete(String id) {
        userDao.delete(id);
    }

    @Override
    public void update(User user) {
        if (user == null) {
            throw new IllegalArgumentException("user must not be null");
        }
        if (Common.isEmpty(user.getId())) {
            throw new IllegalArgumentException("user is not exist");
        }
        if (Common.isEmpty(user.getUsername())) {
            throw new IllegalArgumentException("username must not be null");
        }
        if (querySingleUser(user.getUsername()) != null) {
            throw new IllegalArgumentException("username is exist");
        }
        if (Common.isEmpty(user.getPassword())) {
            throw new IllegalArgumentException("password must not be null");
        }
        if (Common.isEmpty(user.getPassword())) {
            throw new IllegalArgumentException("password must not be null");
        }
        if (Common.isEmpty(user.getStatus())) {
            throw new IllegalArgumentException("status must not be null");
        }
        userDao.update(user);
    }

    @Override
    public User get(String id) {
        return userDao.get(id);
    }

    @Override
    public List<User> list(User user) {
        return userDao.list(user);
    }

    @Override
    public Paging<User> page(User user, int pageStart, int pageSize) {
        return userDao.page(user, pageStart, pageSize);
    }

    @Override
    public int countUser(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        return userDao.list(user).size();
    }

    @Override
    public User querySingleUser(String userName) {
        User user = new User();
        user.setUsername(userName);
        List<User> userList = userDao.list(user);
        //TODO if userList.size()>1 what should be do
        return (userList == null || userList.size() == 0) ? null : userList.get(0);
    }


    @Override
    public List<Role> findbyUserRole(String userId) {
        return userDao.findRoleByUserId(userId);
    }

    @Override
    public boolean isExistUsername(String username) {
        return querySingleUser(username) != null;
    }
}
