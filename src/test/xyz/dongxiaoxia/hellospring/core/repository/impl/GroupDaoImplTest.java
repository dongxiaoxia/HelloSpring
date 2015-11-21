package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.core.entity.Group;
import xyz.dongxiaoxia.hellospring.core.repository.GroupDao;

/**
 * Created by Administrator on 2015/11/8.
 */
public class GroupDaoImplTest extends BasicTest {

    @Autowired
    private GroupDao groupDao;

    @Test
    public void insertTest() {
        Group group = new Group();
        group.setName("技术部");
        group.setParentId("123");
        Assert.assertEquals(1, groupDao.insert(group));
    }

    @Test
    public void deleteTest() {
        Assert.assertEquals(1, groupDao.delete("2"));
    }

    @Test
    public void updateTest() {
        Group group = new Group();
        group.setId("1");
        group.setName("工程部");
        group.setParentId("1234");
        Assert.assertEquals(1, groupDao.update(group));
    }

    @Test
    public void getTest() {
        Assert.assertEquals("工程部", groupDao.get("1").getName());
    }

    @Test
    public void getByParentIdTest() {
        Assert.assertEquals(2, groupDao.getByParentId("1234").size());
    }

    @Test
    public void listTest() {
        Assert.assertEquals(2, groupDao.list(null).size());
    }
}
