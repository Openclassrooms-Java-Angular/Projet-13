package com.jflament.yourcaryourway.controller;

import com.jflament.yourcaryourway.dto.CreateMessageRequest;
import com.jflament.yourcaryourway.dto.MessageResponse;
import com.jflament.yourcaryourway.service.MessageService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/conversations/{conversationId}/messages")
public class MessageController {

    private final MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    public List<MessageResponse> getMessagesByConversation(@PathVariable Long conversationId) {
        return messageService.getMessagesByConversation(conversationId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MessageResponse createMessage(
            @PathVariable Long conversationId,
            @Valid @RequestBody CreateMessageRequest request
    ) {
        return messageService.createMessage(conversationId, request);
    }
}