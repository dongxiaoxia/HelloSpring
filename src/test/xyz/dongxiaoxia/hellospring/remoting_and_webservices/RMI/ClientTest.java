package xyz.dongxiaoxia.hellospring.remoting_and_webservices.RMI;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.remoting.rmi.RmiProxyFactoryBean;
import xyz.dongxiaoxia.hellospring.BasicTest;

/**
 * Created by Administrator on 2015/12/2.
 */
public class ClientTest {

    @Test
    public void start() {
        RmiProxyFactoryBean factory = new RmiProxyFactoryBean();
        factory.setServiceInterface(AccountService.class);
        factory.setServiceUrl("rmi://localhost:1199/AccountService");
        // XXX vincan: 解决重启 rmi 的服务器后会出现拒绝连接或找不到服务对象的错误
        factory.setLookupStubOnStartup(false);
        factory.setRefreshStubOnConnectFailure(true);
        factory.afterPropertiesSet();

        AccountService accountService = (AccountService) factory.getObject();
        accountService.getAccounts("123");
        System.out.println(accountService.getAccounts("123").size());
    }


}
