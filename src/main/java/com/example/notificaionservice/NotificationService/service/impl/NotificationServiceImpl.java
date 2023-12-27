package com.example.notificaionservice.NotificationService.service.impl;

import com.example.notificaionservice.NotificationService.domain.Notification;
import com.example.notificaionservice.NotificationService.domain.NotificationType;
import com.example.notificaionservice.NotificationService.dto.NotificationCreateDto;
import com.example.notificaionservice.NotificationService.repository.NotificationRepository;
import com.example.notificaionservice.NotificationService.repository.NotificationTypeRepository;
import com.example.notificaionservice.NotificationService.service.NotificationService;
import org.springframework.stereotype.Service;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;


@Service
public class NotificationServiceImpl implements NotificationService {

    NotificationRepository notificationRepository;
    NotificationTypeRepository notificationTypeRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository, NotificationTypeRepository notificationTypeRepository) {
        this.notificationRepository = notificationRepository;
        this.notificationTypeRepository = notificationTypeRepository;
    }


    @Override
    public void add(NotificationCreateDto notificationCreateDto) {
        NotificationType notificationType1 = new NotificationType("Activation");
        notificationTypeRepository.save(notificationType1);
        System.out.println(notificationCreateDto);
        Notification notificaton = new Notification(notificationCreateDto.getFirstName(), notificationCreateDto.getLastName(), "", notificationType1, notificationCreateDto.getUsername(), notificationCreateDto.getEmail());
        System.out.println(notificaton);

        String email = "Pozdrav " + notificationCreateDto.getFirstName() + " " + notificationCreateDto.getLastName() + ",\n\n" +
                "Dobrodošli na našu aplikaciju. Molimo Vas da aktivirate Vaš nalog klikom na link ispod.\n\n" +
                notificaton.getLink() + "\n\n" +
                "Srdačan pozdrav,\n" +
                "Tim za podršku\n" +
                "Fitnes Centar";
        sendEmail(notificationCreateDto.getEmail(), "Aktivacija naloga", email);
        notificationRepository.save(notificaton);
    }
    private void sendEmail(String to, String subject, String content) {
        final String username = "ilija.ika.stojmirovic@gmail.com"; // vaš email
        final String password = "gbbp ohsi zfur tiqu"; // vaša lozinka

        Properties prop = new Properties();
        prop.put("mail.smtp.host", "smtp.gmail.com"); // navedite SMTP server
        prop.put("mail.smtp.port", "587");
        prop.put("mail.smtp.auth", "true");
        prop.put("mail.smtp.starttls.enable", "true"); // TLS

        Session session = Session.getInstance(prop, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("ilija.ika.stojmirovic@gmail.com"));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(to)
            );
            message.setSubject(subject);
            message.setText(content);

            Transport.send(message);

            System.out.println("Email uspešno poslat");

        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
