package xyz.dongxiaoxia.hellospring.account.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import xyz.dongxiaoxia.hellospring.core.entity.User;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/11/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:web/WEB-INF/dispatcher-servlet.xml", "classpath:applicationContext.xml"})
public class UserServiceTest {
    @Resource
    UserService userService;

    @Test
    public void getTest() {
        User user = userService.getUser("123");
        Assert.assertEquals(user.getId(), "123");
    }

    @Test
    public void getUserCountTest() {
        Assert.assertEquals(7, userService.getUserCount());
    }
}
