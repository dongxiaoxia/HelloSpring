package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
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
    //final Timestamp lastlogintime = Timestamp.valueOf("2015-05-16 12:12:12");
    final Timestamp lastlogintime = new Timestamp(System.currentTimeMillis());
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
    final Timestamp updateLastlogintime = new Timestamp(System.currentTimeMillis());
    // final Timestamp updateLastlogintime = Timestamp.valueOf("2015-12-16 12:12:12");
    final int updateLevel = 2;
    final String updateAccountType = "02";
    final String updateStatus = "02";
    String userId;
    User user = new User();
    User updateUser = new User();
    @Autowired
    private UserDao userDao;

    @Test
    public void testUser() {
        {
            //添加用户
            userId = String.valueOf(userDao.$save(user));
        }
        checkUserInfo(userId, username, password, nickname, realname, age, sex, email, regtime, lastlogintime, level, accountType, status);

        {
            //更新用户
            updateUser.setId(userId);
            userDao.$update(updateUser);
        }
        checkUserInfo(userId, updateUsername, updatePassword, updateNickname, updateRealname, updateAge, updateSex, updateEmail, updateRegtime, updateLastlogintime, updateLevel, updateAccountType, updateStatus);
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

    @Before
    public void setUp() throws Exception {
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

        updateUser.setId(userId);
        updateUser.setUsername(updateUsername);
        updateUser.setPassword(updatePassword);
        updateUser.setSex(updateSex);
        updateUser.setRealname(updateRealname);
        updateUser.setNickname(updateNickname);
        updateUser.setLevel(updateLevel);
        updateUser.setAccountType(updateAccountType);
        updateUser.setAge(updateAge);
        updateUser.setStatus(updateStatus);
        updateUser.setEmail(updateEmail);
        updateUser.setRegTime(updateRegtime);
        updateUser.setLastLoginTime(updateLastlogintime);
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void test$save() throws Exception {
        int id = userDao.$save(user);
        checkUserInfo(String.valueOf(id), username, password, nickname, realname, age, sex, email, regtime, lastlogintime, level, accountType, status);
    }

    @Test
    public void test$delete() throws Exception {
        String id = String.valueOf(userDao.$save(user));
        int count = userDao.$count(new User());
        userDao.$delete(id, User.class);
        int count1 = userDao.$count(new User());
        Assert.assertEquals(1, count - count1);
    }

    @Test
    public void test$delete1() throws Exception {
        String id1 = String.valueOf(userDao.$save(user));
        String id2 = String.valueOf(userDao.$save(user));
        String id3 = String.valueOf(userDao.$save(user));

        int count = userDao.$count(new User());
        userDao.$delete(new String[]{id1, id2, id3}, User.class);
        int count1 = userDao.$count(new User());
        Assert.assertEquals(3, count - count1);
    }

    @Test
    public void test$update() throws Exception {
        int id = userDao.$save(user);
        updateUser.setId(String.valueOf(id));
        userDao.$update(updateUser);
        checkUserInfo(String.valueOf(id), updateUsername, updatePassword, updateNickname, updateRealname, updateAge, updateSex, updateEmail, updateRegtime, updateLastlogintime, updateLevel, updateAccountType, updateStatus);
    }

    @Test
    public void test$get() throws Exception {
        int id = userDao.$save(user);
        Assert.assertNotNull(userDao.$get(String.valueOf(id), User.class));
        Assert.assertNull(userDao.$get("123123123", User.class));

    }

    @Test
    public void test$query() throws Exception {
        Assert.assertNotNull(userDao.$query(user));
    }

    @Test
    public void test$count() throws Exception {
        userDao.$count(user);
    }

    @Test
    public void test$page() throws Exception {
        int total = userDao.$count(new User());
        Paging paging = userDao.$page(new User(), 1, 100);
        Assert.assertEquals(paging.getTotalRecord(), total);
    }

    @Test
    public void test$batchSave() throws Exception {
        List<User> users = new ArrayList<>();
        for (int a = 0; a < 1000; a++) {
            users.add(user);
        }
        int count1 = userDao.$count(new User());
        long start = System.currentTimeMillis();
        userDao.$batchSave(users, User.class);
        long end = System.currentTimeMillis();
        System.out.println("插入1000条数据花费毫秒数：" + (end - start));
        int count2 = userDao.$count(new User());
        Assert.assertEquals(1000, count2 - count1);
    }

    @Test
    public void test$batchUpdate() throws Exception {
        List<User> users = new ArrayList<>();
        int count = userDao.$count(new User());
        for (int i = 0; i < 1000; i++) {
            User updateUser = new User();
            updateUser.setUsername(updateUsername);
            updateUser.setPassword(updatePassword);
            updateUser.setSex(updateSex);
            updateUser.setRealname(updateRealname);
            updateUser.setNickname(updateNickname);
            updateUser.setLevel(updateLevel);
            updateUser.setAccountType(updateAccountType);
            updateUser.setAge(updateAge);
            updateUser.setStatus(updateStatus);
            updateUser.setEmail(updateEmail);
            updateUser.setRegTime(updateRegtime);
            updateUser.setLastLoginTime(updateLastlogintime);
            updateUser.setId(String.valueOf(count - i));
            users.add(updateUser);
        }
        long start = System.currentTimeMillis();
        userDao.$batchUpdate(users, User.class);
        long end = System.currentTimeMillis();
        System.out.println("更新1000条数据花费毫秒数：" + (end - start));

        for (int i = 0; i < 1000; i++) {
            checkUserInfo(String.valueOf(count - i), updateUsername, updatePassword, updateNickname, updateRealname, updateAge, updateSex, updateEmail, updateRegtime, updateLastlogintime, updateLevel, updateAccountType, updateStatus);
        }
    }
}
