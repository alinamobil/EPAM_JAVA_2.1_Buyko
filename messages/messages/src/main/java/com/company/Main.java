/*
package main.java.com.company;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Main {
    static Properties properties = new Properties();
    static {
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
    }
    public static void main(String[] args) {
        String returnStatement = null;
        try {
            Authenticator auth = new Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("dashkam2000minsk@gmail.com", "dashamisyukevich");
                }
            };
            Session session = Session.getInstance(properties, auth);
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dashkam2000minsk@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress("missukevitch@gmail.com"));
            message.setSubject("Test Mail");
            message.setText("Hi");
            Transport.send(message);
            returnStatement = "The e-mail was sent successfully";
            System.out.println(returnStatement);
        } catch (Exception e) {
            returnStatement = "error in sending mail";
            e.printStackTrace();
        }
    }
}*/
