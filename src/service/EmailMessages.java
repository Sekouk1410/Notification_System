package service;

import interfaces.IMessage;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.*;
import model.Messages;

import java.io.UnsupportedEncodingException;
import java.util.Properties;

public class EmailMessages extends Messages implements IMessage {
    @Override
    public void envoyer(String expediteur, String destinataire,String objet, String contenu) {
        final String username = "notifplusapp@gmail.com";
        final String password = "zilvaneadtyrolmx";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username, expediteur));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(destinataire)
            );
            message.setSubject(objet);
            message.setText(contenu);

            Transport.send(message);
            System.out.println("✅ Email envoyé à " + destinataire);

        } catch (MessagingException | UnsupportedEncodingException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }
}
