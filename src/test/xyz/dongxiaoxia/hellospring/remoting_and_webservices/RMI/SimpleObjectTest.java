package xyz.dongxiaoxia.hellospring.remoting_and_webservices.RMI;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import xyz.dongxiaoxia.hellospring.BasicTest;

import static org.junit.Assert.*;

/**
 * Created by Administrator on 2015/12/2.
 */
public class SimpleObjectTest {

    @Test
    public void test() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext111.xml");
        AccountService accountService = applicationContext.getBean("accountService", AccountService.class);
        accountService.getAccounts("123");
    }
}