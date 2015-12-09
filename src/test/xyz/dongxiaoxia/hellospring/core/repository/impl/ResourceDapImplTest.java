package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.core.entity.Resource;
import xyz.dongxiaoxia.hellospring.core.repository.ResourceDao;

import java.util.List;

/**
 * Created by Administrator on 2015/11/8.
 */
public class ResourceDapImplTest extends BasicTest {

    @Autowired
    private ResourceDao resourceDao;

    @Test
    public void insertTest() {
        Resource resource = new Resource();
        resource.setName("按钮");
        Assert.assertEquals(1, resourceDao.insert(resource));
    }

    @Test
    public void deleteTest() {
        Assert.assertEquals(1, resourceDao.delete("1"));
    }

    @Test
    public void updateTest() {
        Resource resource = new Resource();
        resource.setId("2");
        resource.setName("菜单");
        Assert.assertEquals(1, resourceDao.update(resource));
    }

    @Test
    public void getTest() {
        //Assert.assertEquals("菜单", resourceDao.get("2").getName());
    }

    @Test
    public void listTest() {
        Assert.assertEquals(1, resourceDao.list(null).size());
    }
}
