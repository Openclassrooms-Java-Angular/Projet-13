package com.jflament.yourcaryourway.controller;

import com.jflament.yourcaryourway.dto.ConversationResponse;
import com.jflament.yourcaryourway.dto.CreateConversationRequest;
import com.jflament.yourcaryourway.service.ConversationService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/conversations")
public class ConversationController {

    private final ConversationService conversationService;

    public ConversationController(ConversationService conversationService) {
        this.conversationService = conversationService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ConversationResponse createConversation(@Valid @RequestBody CreateConversationRequest request) {
        return conversationService.createConversation(request);
    }
}