package xyz.dongxiaoxia.hellospring.account.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import xyz.dongxiaoxia.hellospring.util.StringUtils;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;

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
     *
     * @return
     */
    @Secured("ROLE_ADMIN")
    public int getUserCount() {
        return userDao.list().size();
    }

    /**
     * 根基用户名获取用户列表
     *
     * @return
     */
    public User findByUserName(String username) {
        return userDao.findByUsername(username);
    }

    public User login(String username, String password) throws Exception {

        if (StringUtils.isEmpty(username)) {
            throw new Exception("用户名不能为空");
        }
        if (StringUtils.isEmpty(password)) {
            throw new Exception("密码不能为空");
        }
        if (findByUserName(username) == null) {
            throw new Exception("用户名不存在");
        }
        int count = userDao.countUser(username, password);
        if (count == 0) {
            throw new Exception("用户名错误或密码错误");
        }
        return userDao.findByUsername(username);
    }

    public int countUser(String username, String password) {
        return userDao.countUser(username, password);
    }
}
