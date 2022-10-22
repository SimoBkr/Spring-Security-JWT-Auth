package com.peaqock.aml.service.email;

import com.peaqock.aml.config.EmailProps;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EmailSenderService {

    private final EmailSender emailSender;
    private final TemplateEngine templateEngine;
    private final EmailProps props;

    @Value("${spring.mail.public-url}")
    private String publicUrl;
    @Value("${spring.mail.form-url}")
    private String urlForm;
    @Value("${spring.mail.platform-name}")
    private String platformName;

    public void createUser(String email, String password) {
        var context = new Context();
        context.setVariables(Map.of("email", email, "password", password));
        send(email, "AMl Peaqock Invite", "email/user-template.html", context);
    }

    public void addComment(String email, String name) {
        var context = new Context();
        context.setVariables(Map.of("name", name));
        send(email, "AMl Peaqock Ajouter commentaire", "email/comment-template.html", context);
    }

    public void shareForm(String name, String email, UUID idForm) {
        var context = new Context();
        context.setVariables(Map.of(
                "name", name,
                "urlForm", urlForm + idForm
        ));
        send(email, "AMl Peaqock Questionaire", "email/form-kyc.html", context);
    }

    @Async
    public void send(String to, String subject, String templateName,
                     Context context) {
        context.setVariable("subject", subject);
        context.setVariable("publicUrl", publicUrl);
        context.setVariable("platformName", platformName);
        context.setVariable("platformName", platformName);
        emailSender.sendEmail(to, subject, templateEngine.process(templateName, context), Collections.emptyList());
    }
}
