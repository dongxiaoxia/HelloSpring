package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.core.entity.Privilege;
import xyz.dongxiaoxia.hellospring.core.repository.PrivilegeDao;

/**
 * Created by Administrator on 2015/11/8.
 */
public class PrivilegeDaoImplTest extends BasicTest {
    @Autowired
    private PrivilegeDao privilegeDao;

    @Test
    public void insertTest() {
        Privilege privilege = new Privilege();
        privilege.setType("01");
        Assert.assertEquals(1, privilegeDao.insert(privilege));
    }

    @Test
    public void deleteTest() {
        Assert.assertEquals(1, privilegeDao.delete("1"));
    }

    @Test
    public void updateTest() {
        Privilege privilege = new Privilege();
        privilege.setId("2");
        privilege.setType("02");
        Assert.assertEquals(1, privilegeDao.update(privilege));
    }

    @Test
    public void getTest() {
        Assert.assertEquals("02", privilegeDao.get("2").getType());
    }

    @Test
    public void listTest() {
        Assert.assertEquals(2, privilegeDao.list(null).size());
    }

}
