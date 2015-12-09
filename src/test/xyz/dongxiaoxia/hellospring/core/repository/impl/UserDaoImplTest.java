package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.RoleDao;
import xyz.dongxiaoxia.hellospring.core.repository.UserDao;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by Administrator on 2015/11/8.
 */
public class UserDaoImplTest extends BasicTest {

    @Autowired
    private UserDao userDao;

    /*@Before
    public void cleanUser(){
        userDao.cleanAll();
    }*/

    @Test
    public void testUser() {
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

            userId = String.valueOf(userDao.insert(user));
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

            userDao.update(user);
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
        User user = (User) userDao.get(id);
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
    public void $deleteTest() {
        userDao.$delete("123", User.class);
    }

    @Test
    public void $saveTest() {
        userDao.$save(new User());
    }
}
