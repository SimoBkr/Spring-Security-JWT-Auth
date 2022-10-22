package com.peaqock.aml.service.email;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.InternetAddress;
import java.io.File;
import java.util.Date;
import java.util.List;

@Log4j2
@Component
@Profile("!prod")
@RequiredArgsConstructor
public class EmailSenderSmtp implements EmailSender {

    private final JavaMailSender javaMailSender;

    @Value("${spring.mail.from.personal}")
    private String fromPersonal;

    @Value("${spring.mail.from.address}")
    private String fromAddress;

    @Async
    @Override
    public void sendEmail(String to, String subject, String content, List<String> attachments) {
        try {
            final var mail = javaMailSender.createMimeMessage();
            final var helper = new MimeMessageHelper(mail, true, "UTF-8");
            helper.setTo(InternetAddress.parse(to));
            helper.setSubject(subject);
            helper.setText(content, true);
            helper.setSentDate(new Date());
            helper.setFrom(new InternetAddress(fromAddress, fromPersonal));
            attachments.forEach(attachment -> {
                try {
                    final var file = new File(attachment);
                    helper.addAttachment(file.getName(), file);
                } catch (MessagingException e) {
                    log.error("send mail error: " + e.getLocalizedMessage());
                }
            });
            javaMailSender.send(mail);
        } catch (Exception e) {
            log.error("send mail error: " + e.getLocalizedMessage());
        }
    }
}
