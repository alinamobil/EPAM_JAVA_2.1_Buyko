package com.company;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class EmailUtility {
    public static void sendEmail(String email,
                                 String subject, String text) {
        String returnStatement = null;
            Properties props = new Properties();
            props.put("mail.use.tls", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable","true");
            props.put("mail.smtp.host", "smtp.gmail.com");
            props.put("mail.smtp.port", "587");

        try {
            Session session = Session.getInstance(props,  new javax.mail.Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("dashkam2000minsk@gmail.com", "dashamisyukevich");
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dashkam2000minsk@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(subject);
            message.setText(text);
            Transport.send(message);
            returnStatement = "The e-mail was sent successfully";
            System.out.println(returnStatement);
        } catch (Exception e) {
            returnStatement = "error in sending mail";
            e.printStackTrace();
        }
    }
}
