package xyz.dongxiaoxia.hellospring.account.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;
import xyz.dongxiaoxia.hellospring.core.repository.impl.UserDaoImpl;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by chenwendong on 2015/10/29.
 *
 * 用户服务
 */
@Component
@Transactional
public class UserService {
    @Resource
    private UserDao userDao;

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    public int add(User user) {
        return userDao.insert(user);
    }

    /**
     * 删除用户
     *
     * @param id
     * @return
     */
    public int delete(String id) {
        return userDao.delete(id);
    }

    /**
     * 修改用户信息
     *
     * @param user
     * @return
     */
    public int update(User user) {
        return userDao.update(user);
    }

    /**
     * 获取用户
     *
     * @param id
     * @return
     */
    public User get(String id) {
        return userDao.get(id);
    }

    /**
     * 获取所有用户
     *
     * @return
     */
    public List<User> listAll() {
        return userDao.list();
    }

    /**
     * 获取用户数量
     * @return
     */
    public int getUserCount() {
        return userDao.list().size();
    }
}
