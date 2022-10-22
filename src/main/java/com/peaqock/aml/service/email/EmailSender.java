package com.peaqock.aml.service.email;

import java.util.List;

public interface EmailSender {

    void sendEmail(String to, String subject, String content, List<String> attachments);

}
