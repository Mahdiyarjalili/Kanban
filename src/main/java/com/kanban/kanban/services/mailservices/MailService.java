package com.kanban.kanban.services.mailservices;

import org.springframework.context.annotation.Bean;


public interface MailService {
    void sendMail(String to, String subject, String message);
}
