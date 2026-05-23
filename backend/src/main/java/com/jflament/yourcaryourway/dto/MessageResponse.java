package com.jflament.yourcaryourway.dto;

import java.time.LocalDateTime;

public class MessageResponse {

    private final Long id;
    private final String content;
    private final LocalDateTime sentAt;
    private final Long senderId;
    private final Long conversationId;

    public MessageResponse(Long id, String content, LocalDateTime sentAt, Long senderId, Long conversationId) {
        this.id = id;
        this.content = content;
        this.sentAt = sentAt;
        this.senderId = senderId;
        this.conversationId = conversationId;
    }

    public Long getId() {
        return id;
    }

    public String getContent() {
        return content;
    }

    public LocalDateTime getSentAt() {
        return sentAt;
    }

    public Long getSenderId() {
        return senderId;
    }

    public Long getConversationId() {
        return conversationId;
    }
}