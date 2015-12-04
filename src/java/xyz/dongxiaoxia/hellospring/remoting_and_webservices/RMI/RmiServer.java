package xyz.dongxiaoxia.hellospring.remoting_and_webservices.RMI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2015/12/4.
 */
public class RmiServer {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:server.xml");
        while (true) {
        }
    }
}
