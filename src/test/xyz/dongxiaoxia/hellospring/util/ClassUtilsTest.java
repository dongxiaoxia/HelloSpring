package xyz.dongxiaoxia.hellospring.util;

import org.junit.Test;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.entity.User;

/**
 * Created by Administrator on 2015/12/6.
 */
public class ClassUtilsTest {
    @Test
    public void getTableNameTest() {
        System.out.println(ClassUtils.getTableName(Role.class));
    }
}
