package xyz.dongxiaoxia.hellospring.core.repository.impl;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.core.entity.Operation;
import xyz.dongxiaoxia.hellospring.core.repository.OperationDao;

/**
 * Created by Administrator on 2015/11/8.
 */
public class OperationDaoImplTest extends BasicTest {
    @Autowired
    private OperationDao operationDao;

    @Test
    public void insertTest() {
        Operation operation = new Operation();
        operation.setName("删除");
        operation.setCode("02");
        operation.setPrefixUrl("/remove");
        operation.setParentId("0002");
        Assert.assertEquals(1, operationDao.insert(operation));
    }

    @Test
    public void deleteTest() {
        Assert.assertEquals(1, operationDao.delete("1"));
    }

    @Test
    public void updateTest() {
        Operation operation = new Operation();
        operation.setId("2");
        operation.setName("添加");
        operation.setCode("01");
        operation.setParentId("123");
        operation.setPrefixUrl("/create");
        Assert.assertEquals(1, operationDao.update(operation));
    }

    @Test
    public void getTest() {
        Assert.assertEquals("添加", operationDao.get("2").getName());
    }

    @Test
    public void listByParentIdTest() {
        Assert.assertEquals(1, operationDao.listByParentId("123").size());
    }

    @Test
    public void listByPrefixUrlTest() {
        Assert.assertEquals(1, operationDao.listByPrefixUrl("/create").size());
    }

    @Test
    public void listTest() {
        Assert.assertEquals(1, operationDao.list(null).size());
    }

}
