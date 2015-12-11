package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.core.entity.Role;
import xyz.dongxiaoxia.hellospring.core.repository.RoleDao;

/**
 * Created by Administrator on 2015/11/8.
 */
public class RoleDaoImplTest extends BasicTest {

    @Autowired
    private RoleDao roleDao;

    @Test
    public void insertTest() {
        Role role = new Role();
        role.setName("会员");
        Assert.assertEquals(1, roleDao.$save(role));
    }

    @Test
    public void updateTest() {
        Role role = new Role();
        role.setId("2");
        role.setName("一级会员");
        //Assert.assertEquals(1, roleDao.$update(role));
    }

    @Test
    public void getTest() {
        // Assert.assertEquals("普通用户", roleDao.get("1").getName());
    }

    @Test
    public void listTest() {
        Assert.assertEquals(2, roleDao.$query(null).size());
    }
}
