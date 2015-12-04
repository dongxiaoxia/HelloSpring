package xyz.dongxiaoxia.hellospring.remoting_and_webservices.RMI;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by Administrator on 2015/12/4.
 */
public class client {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("classpath:client.xml");
        UserDao userDao = applicationContext.getBean("userDaoImpl_client", UserDao.class);
        if (userDao != null) {
            try {
                System.out.println(userDao.getUser("li"));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
