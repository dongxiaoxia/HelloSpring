package xyz.dongxiaoxia.hellospring.integration.email;

import org.springframework.mail.MailException;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

/**
 * Created by Administrator on 2015/11/29.
 * <p/>
 * Basic MailSender and SimpleMailMessage usage
 */
public class EmailMessage {
    private MailSender mailSender;
    private SimpleMailMessage templateMessage;

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void setTemplateMessage(SimpleMailMessage templateMessage) {
        this.templateMessage = templateMessage;
    }

    public void sendEmail() {
        // Do the business calculations...
// Call the collaborators to persist the order...
// Create a thread safe "copy" of the template message and customize it
        SimpleMailMessage msg = new SimpleMailMessage(this.templateMessage);
        msg.setTo("01dongxiaoxia@gmail.com");
        msg.setText("Dear friend! This is a test!");
        try {
            this.mailSender.send(msg);
        } catch (MailException ex) {
            // simply log it and go on...
            System.err.println(ex.getMessage());
        }
    }
/**
	 * This method will send compose and send the message 
	 * */
	public void sendMail(String to, String subject, String body) 
	{
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(to);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);
	}

	/**
	 * This method will send a pre-configured message
     */
    public void sendPreConfiguredMail(String message)
	{
		SimpleMailMessage mailMessage = new SimpleMailMessage(templateMessage);
		mailMessage.setText(message);
		mailSender.send(mailMessage);
	}

}
