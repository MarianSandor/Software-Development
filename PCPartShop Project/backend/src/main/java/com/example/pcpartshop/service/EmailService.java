package com.example.pcpartshop.service;

import com.example.pcpartshop.dto.OrderDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EmailService {

    private final JavaMailSender javaMailSender;

    public void sendConfirmation(String email, String orderDetails) {
        SimpleMailMessage msg = new SimpleMailMessage();

        //msg.setTo(email);
        msg.setTo("ttestt.eemail@gmail.com");

        msg.setSubject("Order Confirmation");

        StringBuilder content = new StringBuilder("Thank you for ordering from us.\n");
        content.append("The order is processed and then it will be shipped to you.\n");
        content.append("Order details:\n");
        content.append(orderDetails);

        msg.setText(content.toString());

        javaMailSender.send(msg);
    }
}
