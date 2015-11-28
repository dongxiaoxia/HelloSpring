package xyz.dongxiaoxia.hellospring;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * Created by Administrator on 2015/11/5.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"file:web/WEB-INF/dispatcher-servlet.xml", "classpath:applicationContext.xml", "classpath:springSecurity.xml"})
public class BasicTest {

}
