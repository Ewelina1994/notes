package pl.klobut.notesapinew.mail;

import org.springframework.core.env.Environment;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Component;

import java.util.Properties;

@Component
public class FeedbackMailSender implements FeedbackSender {
    private JavaMailSenderImpl mailSender;

    public FeedbackMailSender(Environment environment) {
        mailSender = new JavaMailSenderImpl();

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.debug", "true");
        mailSender.setJavaMailProperties(properties);
        mailSender.setHost(environment.getProperty("spring.mail.host"));
        mailSender.setPort(Integer.parseInt(environment.getProperty("spring.mail.port")));
       // mailSender.setPort(587);
        mailSender.setUsername(environment.getProperty("spring.mail.username"));
        mailSender.setPassword(environment.getProperty("spring.mail.password"));


    }

    @Override
    public boolean sendFeedback(String from, String name, String feedback) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(from);
        message.setSubject("New feedback from " + name);
        message.setText(feedback);
        message.setFrom(from);

        this.mailSender.send(message);
        return false;
    }
}
