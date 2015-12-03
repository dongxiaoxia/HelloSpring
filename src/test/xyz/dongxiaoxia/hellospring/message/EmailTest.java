package xyz.dongxiaoxia.hellospring.message;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import xyz.dongxiaoxia.hellospring.BasicTest;
import xyz.dongxiaoxia.hellospring.integration.email.EmailMessage;
import xyz.dongxiaoxia.hellospring.integration.email.EmailMessageWithJavaMailSender;

/**
 * Created by Administrator on 2015/11/29.
 */
public class EmailTest extends BasicTest {

    @Autowired
    private EmailMessage emailMessage;

    @Autowired
    private EmailMessageWithJavaMailSender emailMessageWithJavaMailSender;

    @Test
    public void sendEmailTest() {
        emailMessage.sendEmail();
    }

    @Test
    public void test() {
        emailMessageWithJavaMailSender.sendEmail();
    }

}
