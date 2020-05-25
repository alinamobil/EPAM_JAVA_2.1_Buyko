package com.company.utility;

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
            props.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
            props.setProperty("mail.smtp.socketFactory.fallback", "false");
            props.setProperty("mail.smtp.port", "465");
            props.setProperty("mail.smtp.socketFactory.port", "465");

        try {
            Session session = Session.getInstance(props,  new javax.mail.Authenticator() {
                public PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication("alinamobil2016@gmail.com", "al21in04ka");
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("alinamobil2016@gmail.com"));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(email));
            message.setSubject(subject);
            message.setText(text);
            Transport transport = session.getTransport("smtp");
            transport.send(message);
            returnStatement = "The e-mail was sent successfully";
            System.out.println(returnStatement);
        } catch (Exception e) {
            returnStatement = "error in sending mail";
            e.printStackTrace();
        }
    }
}
