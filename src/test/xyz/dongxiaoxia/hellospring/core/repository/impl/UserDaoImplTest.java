package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.RoleDao;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;

/**
 * Created by Administrator on 2015/11/8.
 */
public class UserDaoImplTest extends BasicTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void insertTest() {
        User user = new User();
        user.setName("Admin");
        user.setPassword("123456");
        Assert.assertEquals(1, userDao.insert(user));
    }

    @Test
    public void deleteTest() {
        Assert.assertEquals(1, userDao.delete("2"));
    }

    @Test
    public void updateTest() {
        User user = new User();
        user.setId("1");
        user.setName("admin");
        user.setPassword("123456");
        Assert.assertEquals(1, userDao.update(user));
    }

    @Test
    public void getTest() {
        Assert.assertEquals("Admin", userDao.get("1").getName());
    }

    @Test
    public void findByUsernameAndPasswordTest() {
        Assert.assertEquals(1, userDao.findByUsernameAndPassword("admin", "123456").size());
    }

    @Test
    public void findByUsername() {
        Assert.assertEquals(1, userDao.findByUsername("admin").size());
    }

    @Test
    public void listTest() {
        Assert.assertEquals(2, userDao.list().size());
    }
}
