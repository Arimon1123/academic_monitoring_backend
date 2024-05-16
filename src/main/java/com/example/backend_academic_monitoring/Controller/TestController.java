package com.example.backend_academic_monitoring.Controller;

import com.example.backend_academic_monitoring.Service.EmailService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.context.Context;


@RestController
@RequestMapping("/test")
public class TestController {
    private final EmailService emailService;

    public TestController(EmailService emailService) {
        this.emailService = emailService;
    }

    @GetMapping("/send-email")
    public String sendEmail() {
        Context context = new Context();
        context.setVariable("username", "username");
        context.setVariable("password", "password");
        emailService.sendPasswordEmail("arimonmon999@gmail.com", "test", context);
        return "email Sent";
    }
}
