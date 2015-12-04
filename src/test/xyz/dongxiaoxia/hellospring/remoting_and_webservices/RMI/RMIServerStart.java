package xyz.dongxiaoxia.hellospring.remoting_and_webservices.RMI;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Administrator on 2015/12/4.
 */
public class RMIServerStart {
    public static void main(String[] args) throws IOException {

        new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        while (true) {
            if (reader.readLine().equals("exit")) {
                System.exit(0);
            }
        }

    }
}
