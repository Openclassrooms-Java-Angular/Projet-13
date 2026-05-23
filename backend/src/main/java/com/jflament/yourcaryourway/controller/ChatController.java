package com.jflament.yourcaryourway.controller;

import com.jflament.yourcaryourway.dto.ChatMessageRequest;
import com.jflament.yourcaryourway.dto.CreateMessageRequest;
import com.jflament.yourcaryourway.dto.MessageResponse;
import com.jflament.yourcaryourway.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class ChatController {

    private final MessageService messageService;
    private final SimpMessagingTemplate messagingTemplate;

    public ChatController(MessageService messageService, SimpMessagingTemplate messagingTemplate) {
        this.messageService = messageService;
        this.messagingTemplate = messagingTemplate;
    }

    @MessageMapping("/chat.send")
    public void sendMessage(@Valid @Payload ChatMessageRequest request) {
        CreateMessageRequest createMessageRequest = new CreateMessageRequest();
        createMessageRequest.setContent(request.getContent());
        createMessageRequest.setSenderId(request.getSenderId());

        MessageResponse savedMessage = messageService.createMessage(
                request.getConversationId(),
                createMessageRequest
        );

        messagingTemplate.convertAndSend(
                "/topic/conversations/" + request.getConversationId(),
                savedMessage
        );
    }
}