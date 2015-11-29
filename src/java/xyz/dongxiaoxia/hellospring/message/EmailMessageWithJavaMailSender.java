package xyz.dongxiaoxia.hellospring.message;

import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
 * Created by Administrator on 2015/11/29.
 * <p/>
 * Using the JavaMailSender and the MimeMessagePreparator
 */
public class EmailMessageWithJavaMailSender {
    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail() {
        // Do the business calculations..
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            @Override
            public void prepare(MimeMessage mimeMessage) throws Exception {
                mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress("01dongxiaoxia@gmail.com"));
                mimeMessage.setFrom(new InternetAddress("810196858@qq.com"));
                mimeMessage.setText("Dear friend! This is a test!");
            }
        };
        try {
            //this.mailSender.send(preparator);


            // Using the JavaMail MimeMessageHelper
            // of course you would use DI in any real-world cases
            //JavaMailSenderImpl sender = new JavaMailSenderImpl();
//            MimeMessage message = mailSender.createMimeMessage();
//            MimeMessageHelper helper = new MimeMessageHelper(message);
//            helper.setFrom("810196858@qq.com");
//            helper.setTo("01dongxiaoxia@gmail.com");
//            helper.setText("Thank you for ordering!");
//            mailSender.send(message);


            //Sending attachments and inline resources
//            MimeMessage message = mailSender.createMimeMessage();
//// use the true flag to indicate you need a multipart message
//            MimeMessageHelper helper = new MimeMessageHelper(message, true);
//            helper.setFrom("810196858@qq.com");
//            helper.setTo("810196858@qq.com");
//            helper.setText("Thank you for ordering!");
//// let's attach the infamous windows Sample file (this time copied to c:/)
//            FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\fgdf.jpg"));
//            helper.addAttachment("CoolImage.jpg", file);
//            mailSender.send(message);


            //Using the JavaMail MimeMessageHelper
            // Sending attachments and inline resources
            MimeMessage message = mailSender.createMimeMessage();
// use the true flag to indicate you need a multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom("810196858@qq.com");
            helper.setTo("810196858@qq.com");
            helper.setText("Thank you for ordering!");
// use the true flag to indicate the text included is HTML
            helper.setText("<html><body>sdf<img src='cid:identifier1234'></body></html>", true);
// let's include the infamous windows Sample file (this time copied to c:/)
            FileSystemResource res = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\fgdf.jpg"));
            helper.addInline("identifier1234", res);
            mailSender.send(message);


//            Warning
//            Inline  resources  are  added  to  the  mime  message  using  the  specified  Content-ID  (
//                    identifier1234  in  the above example). The order  in which you are adding  the  text and  the
//            resource are very important. Be sure to first add the text and after that the resources. If you are
//            doing it the other way around, it won’t work!
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        } catch (MessagingException e) {
            e.printStackTrace();
        }

    }
}
