package al.algorthyhm.utils;


import al.algorthyhm.service.ExcelService;
import jakarta.mail.*;
import jakarta.mail.internet.AddressException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EmailUtils {

    private final static Logger logger = LogManager.getLogger(ExcelService.class);

    public static void sendEmail(List<InternetAddress> emailList) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        executor.submit(() -> {
            try {
                send(emailList);
            } catch (MessagingException e) {
                logger.info( "Failed to send email", e);
            }
        });
        executor.shutdown();
    }

    private static void send(List<InternetAddress> emailList) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        String username = "l.mesi@algorhythm.al";
        String password = "rivuzbdrkkmycvsm";

        Session session = null;
        try {
            session = Session.getInstance(props, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });
        } catch (Exception e) {
            logger.info( "Failed to create session", e);
            throw new MessagingException("Failed to create session", e);
        }

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress(username));
        message.setRecipients(Message.RecipientType.BCC, emailList.toArray(new InternetAddress[0]));

        message.setSubject("Test Email");
        message.setText("Pershendetje, ky eshte nje email i generuar nga Lejrat Mesi");

        try {
            Transport.send(message);
            logger.info("Email send..");
        } catch (Exception e) {
            logger.info(  "Failed to send email", e);
            throw new MessagingException("Failed to send email", e);
        }
    }
}
