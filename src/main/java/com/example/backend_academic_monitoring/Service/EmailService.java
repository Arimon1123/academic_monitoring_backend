package com.example.backend_academic_monitoring.Service;

import org.thymeleaf.context.Context;

public interface EmailService {
    public void sendEmail(String to, String subject, String text);
    public void sendPasswordEmail(String to, String subject, Context context);
}
