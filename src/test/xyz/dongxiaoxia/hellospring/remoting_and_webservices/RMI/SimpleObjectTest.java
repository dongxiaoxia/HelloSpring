package xyz.dongxiaoxia.hellospring.remoting_and_webservices.RMI;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2015/12/2.
 */
public class SimpleObjectTest extends BasicTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void test() {
        Assert.assertNull(accountService.getAccounts("123"));
    }
}