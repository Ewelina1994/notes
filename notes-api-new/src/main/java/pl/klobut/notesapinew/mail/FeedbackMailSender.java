package pl.klobut.notesapinew.mail;
import com.sun.org.apache.xalan.internal.xsltc.compiler.Template;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;
import sun.plugin2.message.transport.Transport;

import javax.mail.BodyPart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.util.Properties;

@Component
public class FeedbackMailSender implements FeedbackSender{
    private JavaMailSenderImpl  mailSender;

    public FeedbackMailSender(Environment environment) {
        mailSender = new JavaMailSenderImpl();

//        Properties props = new Properties();
//        props.setProperty("mail.transport.protocol", environment.getProperty("spring.mail.host"));
//        props.setProperty("mail.host", environment.getProperty("spring.mail.port"));
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.port", "465");
//        props.put("mail.smtp.protocol", "smtp");
//        props.put("mail.debug", "true");
//        props.put("mail.smtp.socketFactory.port", "465");
//        props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//        props.put("mail.smtp.socketFactory.fallback", "false");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.smtp.username", "ewelinakobut@gmail.com");
//        props.put("mail.smtp.password", "Rowerek1");
//        mailSender.setJavaMailProperties(props);

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        mailSender.setJavaMailProperties(properties);
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));
    }

    @Override
    public boolean sendFeedback(String from, String name, String feedback){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(from);
        message.setSubject("New feedback from " + name);
        message.setText(feedback);
        message.setFrom(from);

        this.mailSender.send(message);
        return false;
    }
}
