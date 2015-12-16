package xyz.dongxiaoxia.hellospring.service.impl;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.service.UserService;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2015/12/16.
 */
public class UserServiceImplTest extends BasicTest {

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
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    String userId;
    User user = new User();
    User updateUser = new User();
    @Autowired
    private UserService userService;

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
        User user = userService.get(id);
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

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testAdd() throws Exception {
        user.setUsername("ooooo");
        userId = userService.add(user);
        checkUserInfo(userId, "ooooo", password, nickname, realname, age, sex, email, regtime, lastlogintime, level, accountType, status);
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("username is exist");
        userId = userService.add(user);

        user.setUsername(null);
        expectedEx.expect(IllegalArgumentException.class);
        expectedEx.expectMessage("username is exist");
        userId = userService.add(user);
    }

    @Test
    public void testDelete() throws Exception {
        user.setUsername("test");
        userId = userService.add(user);
        Assert.assertNotNull(userService.get(userId));
        userService.delete(userId);
        Assert.assertNull(userService.get(userId));
    }

    @Test
    public void testUpdate() throws Exception {

    }

    @Test
    public void testGet() throws Exception {

    }

    @Test
    public void testList() throws Exception {

    }

    @Test
    public void testCountUser() throws Exception {

    }

    @Test
    public void testQuerySingleUser() throws Exception {

    }

    @Test
    public void testFindbyUserRole() throws Exception {

    }
}