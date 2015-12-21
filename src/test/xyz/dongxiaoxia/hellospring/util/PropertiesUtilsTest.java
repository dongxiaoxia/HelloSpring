package xyz.dongxiaoxia.hellospring.util;

import java.util.Properties;

/**
 * Created by Administrator on 2015/12/20.
 */
public class PropertiesUtilsTest {

    public static void main() throws Exception {
        Properties properties = new Properties();

        properties.load(PropertiesUtilsTest.class.getResourceAsStream("/jdbc.properties"));//路径前面加"/"表示classpath绝对路径，不加"/"表示当前类的路径
        //静态方法不能用this
        //properties.load(this.getClass().getResourceAsStream("/jdbc.properties"));

        Properties properties1 = new Properties(properties);
        properties1.load(PropertiesUtilsTest.class.getClassLoader().getResourceAsStream("jdbc.properties"));
        //静态方法不能用this
        //properties1.load(this.getClass().getClassLoader().getResourceAsStream("jdbc.properties"));

        Properties properties2 = new Properties();
        properties2.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("jdbc.properties"));

        System.out.println(properties);
    }
}