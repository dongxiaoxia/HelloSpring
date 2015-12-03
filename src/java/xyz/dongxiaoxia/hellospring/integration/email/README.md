#Spring Email Integration

##1.Basic MailSender and SimpleMailMessage usage

        //code demo
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
             */
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
     //Find below the bean definitions for the above code:
     <bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
            <property name="host" value="smtp.qq.com"/>
            <property name="port" value="25"/>
            <property name="username" value="810196858@qq.com"/>
            <property name="password" value="****"/>
            <property name="javaMailProperties">
                <props>
                    <prop key="mail.smtp.auth">true</prop>
                </props>
            </property>
     </bean>
    <!-- this is a template message that we can pre-load with default state -->
    <!-- You can have some pre-configured messagess also which are ready to send -->
    <bean id="templateMessage" class="org.springframework.mail.SimpleMailMessage">
        <property name="from" value="810196858@qq.com"/>
        <property name="subject" value="Your order"/>
    </bean>
    <bean id="EmailMessage" class="xyz.dongxiaoxia.hellospring.integration.email.EmailMessage">
        <property name="mailSender" ref="mailSender"/>
        <property name="templateMessage" ref="templateMessage"/>
    </bean>

##2.Using the JavaMailSender and the MimeMessagePreparator
    //code demo
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
                //MimeMessage message = mailSender.createMimeMessage();
                //MimeMessageHelper helper = new MimeMessageHelper(message);
                //helper.setFrom("810196858@qq.com");
                //helper.setTo("01dongxiaoxia@gmail.com");
                //helper.setText("Thank you for ordering!");
                //mailSender.send(message);


                //Sending attachments and inline resources
                //MimeMessage message = mailSender.createMimeMessage();
                // use the true flag to indicate you need a multipart message
                //MimeMessageHelper helper = new MimeMessageHelper(message, true);
                //helper.setFrom("810196858@qq.com");
                //helper.setTo("810196858@qq.com");
                //helper.setText("Thank you for ordering!");
                // let's attach the infamous windows Sample file (this time copied to c:/)
                //FileSystemResource file = new FileSystemResource(new File("C:\\Users\\Administrator\\Desktop\\fgdf.jpg"));
                //helper.addAttachment("CoolImage.jpg", file);
                //mailSender.send(message);


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


                //Warning
                //Inline  resources  are  added  to  the  mime  message  using  the  specified  Content-ID  (
                //identifier1234  in  the above example). The order  in which you are adding  the  text and  the
                //resource are very important. Be sure to first add the text and after that the resources. If you are
                //doing it the other way around, it won’t work!
            } catch (MailException ex) {
                // simply log it and go on...
                System.err.println(ex.getMessage());
            } catch (MessagingException e) {
                e.printStackTrace();
            }
        }
    }
    // Find below the bean definitions for the above code:
     <bean id="emailMessageWithJavaMailSender"
              class="xyz.dongxiaoxia.hellospring.integration.email.EmailMessageWithJavaMailSender">
            <property name="mailSender" ref="mailSender"/>
     </bean>
     <bean id="mailSender" class="org.springframework.mail.javamail.JavaMailSenderImpl">
            <property name="host" value="smtp.qq.com"/>
            <property name="port" value="25"/>
            <property name="username" value="810196858@qq.com"/>
            <property name="password" value="qqsycysb201314.."/>
            <property name="javaMailProperties">
                <props>
                    <prop key="mail.smtp.auth">true</prop>
                </props>
            </property>
      </bean>

##3.Using the JavaMail MimeMessageHelper
    // of course you would use DI in any real-world cases
    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    sender.setHost("mail.host.com");
    MimeMessage message = sender.createMimeMessage();
    MimeMessageHelper helper = new MimeMessageHelper(message);
    helper.setTo("test@host.com");
    helper.setText("Thank you for ordering!");
    sender.send(message);

1）Sending attachments and inline resources
    Attachments
The following example shows you how to use the MimeMessageHelper to send an email along with
a single JPEG image attachment
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("mail.host.com");
        MimeMessage message = sender.createMimeMessage();
        // use the true flag to indicate you need a multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo("test@host.com");
        helper.setText("Check out this image!");
        // let's attach the infamous windows Sample file (this time copied to c:/)
        FileSystemResource file = new FileSystemResource(new File("c:/Sample.jpg"));
        helper.addAttachment("CoolImage.jpg", file);
        sender.send(message);

    Inline resources

    The following example shows you how to use the MimeMessageHelper to send an email along with
    an inline image.

    JavaMailSenderImpl sender = new JavaMailSenderImpl();
    sender.setHost("mail.host.com");
    MimeMessage message = sender.createMimeMessage();
    // use the true flag to indicate you need a multipart message
    MimeMessageHelper helper = new MimeMessageHelper(message, true);
    helper.setTo("test@host.com");
    // use the true flag to indicate the text included is HTML
    helper.setText("<html><body><img src=''cid:identifier1234''></body></html>", true);
    // let's include the infamous windows Sample file (this time copied to c:/)
    FileSystemResource res = new FileSystemResource(new File("c:/Sample.jpg"));
    helper.addInline("identifier1234", res);
    sender.send(message);
2）Creating email content using a templating library
未完成这个功能

