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
        user.setUsername("Admin");
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
        user.setUsername("admin");
        user.setPassword("123456");
        Assert.assertEquals(1, userDao.update(user));
    }

    @Test
    public void getTest() {
        Assert.assertEquals("Admin", userDao.get("1").getUsername());
    }

    @Test
    public void listTest() {
        //Assert.assertEquals(2, userDao.list().size());
    }

    @Test
    public void addTest() {
        User user = new User();
        user.setUsername("123");
        user.setPassword("123");
        user.setStatus("01");
        user.setAccountType("01");
        user.setId("0001");
        user.setAge(12);
        user.setEmail("810196858@qq.com");
        user.setLevel(1);
        user.setNickname("dongxiaoxia");
        user.setRealname("陈文东");
        user.setSex("01");
        userDao.insert(user);
    }
}
