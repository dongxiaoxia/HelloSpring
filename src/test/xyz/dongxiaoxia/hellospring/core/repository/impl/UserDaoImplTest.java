package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;
import xyz.dongxiaoxia.hellospring.util.Paging;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
public class UserDaoImplTest extends BasicTest {

    final String username = "张三丰";
    final String password = "123456";
    final String nickname = "阿三";
    final String realname = "张三";
    final int age = 16;
    final int sex = 1;
    final String email = "9876@543.21";
    //final Timestamp regtime = new Timestamp(System.currentTimeMillis());
    final Timestamp regtime = Timestamp.valueOf("2015-02-16 12:12:12");
    final Timestamp lastlogintime = Timestamp.valueOf("2015-05-16 12:12:12");
    //final Timestamp lastlogintime = new Timestamp(System.currentTimeMillis());
    final int level = 1;
    final String accountType = "01";
    final String status = "01";
    final String updateUsername = "张君宝";
    final String updatePassword = "67890";
    final String updateNickname = "啊宝";
    final String updateRealname = "张小宝";
    final int updateAge = 45;
    final int updateSex = 2;
    final String updateEmail = "111111@543.21";
    // final Timestamp updateRegtime = new Timestamp(System.currentTimeMillis());
    final Timestamp updateRegtime = Timestamp.valueOf("2015-12-11 12:12:12");
    // final Timestamp updateLastlogintime = new Timestamp(System.currentTimeMillis());
    final Timestamp updateLastlogintime = Timestamp.valueOf("2015-12-16 12:12:12");
    final int updateLevel = 2;
    final String updateAccountType = "02";
    final String updateStatus = "02";
    String userId;
    @Autowired
    private UserDao userDao;

    @Test
    public void testUser() {
        {
            //添加用户
            User user = new User();
            user.setUsername(username);
            user.setPassword(password);
            user.setSex(sex);
            user.setRealname(realname);
            user.setNickname(nickname);
            user.setLevel(level);
            user.setAccountType(accountType);
            user.setAge(age);
            user.setStatus(status);
            user.setEmail(email);
            user.setRegTime(regtime);
            user.setLastLoginTime(lastlogintime);
            userId = String.valueOf(userDao.$save(user));
        }
        checkUserInfo(userId, username, password, nickname, realname, age, sex, email, regtime, lastlogintime, level, accountType, status);

        {
            //更新用户
            User user = new User();
            user.setId(userId);
            user.setUsername(updateUsername);
            user.setPassword(updatePassword);
            user.setSex(updateSex);
            user.setRealname(updateRealname);
            user.setNickname(updateNickname);
            user.setLevel(updateLevel);
            user.setAccountType(updateAccountType);
            user.setAge(updateAge);
            user.setStatus(updateStatus);
            user.setEmail(updateEmail);
            user.setRegTime(updateRegtime);
            user.setLastLoginTime(updateLastlogintime);
            userDao.$update(user);
        }
        checkUserInfo(userId, updateUsername, updatePassword, updateNickname, updateRealname, updateAge, updateSex, updateEmail, updateRegtime, updateLastlogintime, updateLevel, updateAccountType, updateStatus);

        //删除用户
        userDao.$delete(userId, User.class);
        Assert.assertNull("the user where id = " + userId, userDao.$get(userId, User.class));
    }

    //获取用户，并验证数据
    private void checkUserInfo(
            String id,
            String username,
            String password,
            String nickname,
            String realname,
            int age,
            int sex,
            String email,
            Timestamp regtime,
            Timestamp lastlogintime,
            int level,
            String accountType,
            String status
    ) {
        User user = userDao.$get(id, User.class);
        Assert.assertEquals(id, user.getId());
        Assert.assertEquals(username, user.getUsername());
        Assert.assertEquals(password, user.getPassword());
        Assert.assertEquals(nickname, user.getNickname());
        Assert.assertEquals(realname, user.getRealname());
        Assert.assertEquals(age, user.getAge());
        Assert.assertEquals(sex, user.getSex());
        Assert.assertEquals(email, user.getEmail());
        Assert.assertEquals(regtime, user.getRegTime());
        Assert.assertEquals(lastlogintime, user.getLastLoginTime());
        Assert.assertEquals(accountType, user.getAccountType());
        Assert.assertEquals(status, user.getStatus());
    }

    @Test
    public void $countTest() {
        User user = new User();
        user.setNickname("sdaf");
        System.out.println(userDao.$count(user));
    }

    @Test
    public void $queryTest() {
        User user = new User();
        user.setId("23");
        System.out.println(userDao.$query(user).size());
    }

    @Test
    public void $pageTest() {
        User user = new User();
        user.setId("23");
        Paging paging = userDao.$page(user, 1, 10);
        System.out.println(paging);
    }

    @Test
    public void $batchUpdateTest() {
        List<User> users = new ArrayList<>();
        for (int a = 0; a < 1000; a++) {
            User user = new User();
            user.setId(String.valueOf(a));
            user.setPassword("123");
            user.setUsername("dongxiaoxia");
            users.add(user);
        }
        long start = System.currentTimeMillis();
        userDao.$batchUpdate(users, User.class);
        long end = System.currentTimeMillis();
        System.out.println(end - start);

    }

    @Test
    public void $batchSaveTest() {
        List<User> users = new ArrayList<>();
        long start1 = System.currentTimeMillis();
        for (int a = 0; a < 1000; a++) {
            User user = new User();
            user.setPassword("123");
            user.setUsername("dongxiaoxia");
            users.add(user);
            userDao.$save(user);
        }
        long end1 = System.currentTimeMillis();
        System.out.println(end1 - start1);

        long start = System.currentTimeMillis();
        userDao.$batchSave(users, User.class);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }

}
