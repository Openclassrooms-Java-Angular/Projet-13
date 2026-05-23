package com.jflament.yourcaryourway.service;

import com.jflament.yourcaryourway.dto.CreateMessageRequest;
import com.jflament.yourcaryourway.dto.MessageResponse;
import com.jflament.yourcaryourway.entity.Conversation;
import com.jflament.yourcaryourway.entity.Message;
import com.jflament.yourcaryourway.entity.User;
import com.jflament.yourcaryourway.repository.ConversationRepository;
import com.jflament.yourcaryourway.repository.MessageRepository;
import com.jflament.yourcaryourway.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;
    private final ConversationRepository conversationRepository;
    private final UserRepository userRepository;

    public MessageService(
            MessageRepository messageRepository,
            ConversationRepository conversationRepository,
            UserRepository userRepository
    ) {
        this.messageRepository = messageRepository;
        this.conversationRepository = conversationRepository;
        this.userRepository = userRepository;
    }

    public List<MessageResponse> getMessagesByConversation(Long conversationId) {
        return messageRepository.findByConversationIdOrderBySentAtAsc(conversationId)
                .stream()
                .map(message -> new MessageResponse(
                        message.getId(),
                        message.getContent(),
                        message.getSentAt(),
                        message.getSender().getId(),
                        message.getConversation().getId()
                ))
                .toList();
    }

    public MessageResponse createMessage(Long conversationId, CreateMessageRequest request) {
        Conversation conversation = conversationRepository.findById(conversationId)
                .orElseThrow(() -> new RuntimeException("Conversation not found"));

        User sender = userRepository.findById(request.getSenderId())
                .orElseThrow(() -> new RuntimeException("Sender not found"));

        Message message = new Message();
        message.setContent(request.getContent());
        message.setConversation(conversation);
        message.setSender(sender);

        Message saved = messageRepository.save(message);

        return new MessageResponse(
                saved.getId(),
                saved.getContent(),
                saved.getSentAt(),
                saved.getSender().getId(),
                saved.getConversation().getId()
        );
    }
}