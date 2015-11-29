package xyz.dongxiaoxia.hellospring.message;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import xyz.dongxiaoxia.hellospring.BasicTest;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

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
