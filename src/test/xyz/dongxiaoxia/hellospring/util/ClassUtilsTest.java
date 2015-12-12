package xyz.dongxiaoxia.hellospring.util;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.core.repository.persistence.Finder;

import java.sql.Timestamp;

/**
 * Created by Administrator on 2015/12/6.
 */
public class ClassUtilsTest {


    final String id = "1";
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
    @Rule
    public ExpectedException expectedEx = ExpectedException.none();
    User user = new User();

    @Before
    public void init() {
        user.setId(id);
        user.setPassword(password);
        user.setUsername(username);
        user.setAccountType(accountType);
        user.setAge(age);
        user.setEmail(email);
        user.setLastLoginTime(lastlogintime);
        user.setLevel(level);
        user.setNickname(nickname);
        user.setRealname(realname);
        user.setRegTime(regtime);
        user.setSex(sex);
        user.setStatus(status);
    }

    @Test
    public void isEntityAnnotationPresentTest() {
        Assert.assertEquals(true, Finder.isEntityAnnotationPresent(User.class));
        Assert.assertEquals(false, Finder.isEntityAnnotationPresent(Role.class));
    }

    @Test
    public void getTableNameTest() {
        System.out.println(Finder.getTableName(User.class));
        expectedEx.expect(NullPointerException.class);
        expectedEx.expectMessage("is not used Entity annotation");
        System.out.println(Finder.getTableName(Role.class));
    }

    @Test
    public void getIdentityNameTest() throws Exception {
        System.out.println(Finder.getIdentityName(User.class));
        expectedEx.expect(Exception.class);
        expectedEx.expectMessage("is not used Entity annotation");
        System.out.println(Finder.getIdentityName(Role.class));
    }

    @Test
    public void getIdentityValueTest() throws Exception {
        User user1 = new User();
        user1.setId("123");

        User user2 = new User();
        System.out.println(Finder.getIdentityValue(user1));
        System.out.println(Finder.getIdentityValue(user2));
        expectedEx.expect(Exception.class);
        expectedEx.expectMessage("is not used Entity annotation");
        System.out.println(Finder.getIdentityValue(new Role()));
    }

    @Test
    public void testExecuteQuery() {


        User user1 = new User();
        user1.setId(id);//根据id查询

        User user2 = new User();
        user2.setUsername(username);//根据用户名查询

        User user3 = new User();
        user3.setUsername(username);
        user3.setPassword(password);//根据用户名、密码组合查询

        User user4 = new User();
        user4.setId("1,2,3,4");//根据id数组查询

        String sql1 = Finder.getQuerySql(user1);
        String sql2 = Finder.getQuerySql(user2);
        String sql3 = Finder.getQuerySql(user3);
        String sql4 = Finder.getQuerySql(user4);

        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);
        System.out.println(sql4);

    }

    @Test
    public void getInsertSqlTest() {
        System.out.println(Finder.getInsertSql(user));
    }

    @Test
    public void getUpdateSqlTest() {
        System.out.println(Finder.getUpdateSql(user));
    }

    @Test
    public void getQuerySqlTest() {
        System.out.println(Finder.getQuerySql(user));
    }

    @Test
    public void getSelectByIdentitySqlTest() {
        System.out.println(Finder.getSelectByIdentitySql("123", User.class));
        expectedEx.expect(Exception.class);
        expectedEx.expectMessage("is not used Entity annotation");
        System.out.println(Finder.getSelectByIdentitySql("123", Role.class));
    }

    @Test
    public void getInSqlTest() {
        String[] ids = new String[]{"1", "2", "3"};
        System.out.println(Finder.getDeleteSql("1", User.class));
        System.out.println(Finder.getDeleteSql(ids, User.class));
    }

    @Test
    public void getCountSqlTest() {
        System.out.println(Finder.getCountSql(user));
    }

    @Test
    public void getPageSqlTest() {
        System.out.println(Finder.getPageSql(user, 3, 10));
    }

    @Test
    public void getBatchSaveSqlTest() {
        System.out.println(Finder.getBatchSave(User.class));
    }
}
