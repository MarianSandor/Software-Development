package com.example.pcpartshop.controller;

import com.example.pcpartshop.dto.message.ClientMessage;
import com.example.pcpartshop.dto.message.EmployeeMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.web.util.HtmlUtils;

import static com.example.pcpartshop.UrlMapping.*;

@Controller
public class MessageController {
    @MessageMapping(MESSAGE)
    @SendTo(TOPIC + ALERTS)
    public EmployeeMessage alert(ClientMessage message) throws Exception {
        Long clientId = Long.valueOf(HtmlUtils.htmlEscape(message.getClientId()));
        String username = HtmlUtils.htmlEscape(message.getUsername());

        return EmployeeMessage.builder()
                .clientId(clientId)
                .content(username + " needs help for building a configuration")
                .build();
    }
}
