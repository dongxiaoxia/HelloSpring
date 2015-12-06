package xyz.dongxiaoxia.hellospring.util;

import org.junit.Test;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;
import xyz.dongxiaoxia.hellospring.util.annotation.Column;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Timestamp;
import java.util.Objects;

/**
 * Created by Administrator on 2015/12/6.
 */
public class ClassUtilsTest {
    @Test
    public void getTableNameTest() {
        System.out.println(ClassUtils.getTableName(Role.class));
    }

    @Test
    public void testExecuteQuery() {

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

        User user1 = new User();
        user1.setId(id);//根据id查询

        User user2 = new User();
        user2.setUsername(username);//根据用户名查询

        User user3 = new User();
        user3.setUsername(username);
        user3.setPassword(password);//根据用户名、密码组合查询

        User user4 = new User();
        user4.setId("1,2,3,4");//根据id数组查询

        String sql1 = ClassUtils.executeQuery(user1);
        String sql2 = ClassUtils.executeQuery(user2);
        String sql3 = ClassUtils.executeQuery(user3);
        String sql4 = ClassUtils.executeQuery(user4);

        System.out.println(sql1);
        System.out.println(sql2);
        System.out.println(sql3);
        System.out.println(sql4);

    }
}
