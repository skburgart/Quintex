package com.quintex.utility;

import java.util.Formatter;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Steven Burgart
 */
public class Email {

    private static final String SMTP_HOST_NAME = "smtp.gmail.com";
    private static final String SMTP_PORT = "465";
    private static final String SSL_FACTORY = "javax.net.ssl.SSLSocketFactory";
    private static final String FROM_EMAIL = "quintex.noreply@gmail.com";
    private static final String RESET_SUBJECT = "Password Reset at Quintex";
    private static final String RESET_BODY = "Dear %s,\n\nYou have recently requested to reset your password at Quintex. \nYour new password is: %s\n\n- Quintex";

    public static void resetPassword(String email, String username, String newPassword) {

        Formatter f = new Formatter();
        String body = f.format(RESET_BODY, username, newPassword).toString();

        send(email, RESET_SUBJECT, body);
    }

    private static void send(String to, String subject, String message) {

        Properties props = new Properties();
        props.put("mail.smtp.host", SMTP_HOST_NAME);
        props.put("mail.smtp.auth", "true");
        props.put("mail.debug", "false");
        props.put("mail.smtp.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.port", SMTP_PORT);
        props.put("mail.smtp.socketFactory.class", SSL_FACTORY);
        props.put("mail.smtp.socketFactory.fallback", "false");

        Session session = Session.getDefaultInstance(props,
                new javax.mail.Authenticator() {

                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(FROM_EMAIL, "quintex123");
                    }
                });

        Message msg = new MimeMessage(session);
        try {
            InternetAddress addressFrom = new InternetAddress(FROM_EMAIL);
            InternetAddress addressTo = new InternetAddress(to);

            msg.setFrom(addressFrom);
            msg.setRecipient(Message.RecipientType.TO, addressTo);

            msg.setSubject(subject);
            msg.setContent(message, "text/plain");
            Transport.send(msg);
        } catch (MessagingException exp) {
            Logger.logError(exp);
        }
    }
}
