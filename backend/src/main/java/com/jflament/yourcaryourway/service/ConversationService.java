package com.jflament.yourcaryourway.service;

import com.jflament.yourcaryourway.dto.ConversationResponse;
import com.jflament.yourcaryourway.dto.CreateConversationRequest;
import com.jflament.yourcaryourway.entity.Conversation;
import com.jflament.yourcaryourway.entity.User;
import com.jflament.yourcaryourway.repository.ConversationRepository;
import com.jflament.yourcaryourway.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class ConversationService {

    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public ConversationService(ConversationRepository conversationRepository, UserRepository userRepository) {
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    public ConversationResponse createConversation(CreateConversationRequest request) {
        User customer = userRepository.findById(request.getCustomerId())
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Conversation conversation = new Conversation();
        conversation.setSubject(request.getSubject());
        conversation.setCustomer(customer);

        Conversation saved = conversationRepository.save(conversation);

        return new ConversationResponse(
                saved.getId(),
                saved.getSubject(),
                saved.getStatus().name(),
                saved.getCreatedAt(),
                saved.getCustomer().getId()
        );
    }
}