package com.jflament.yourcaryourway.dto;

import java.time.LocalDateTime;

public class ConversationResponse {

    private final Long id;
    private final String subject;
    private final String status;
    private final LocalDateTime createdAt;
    private final Long customerId;

    public ConversationResponse(Long id, String subject, String status, LocalDateTime createdAt, Long customerId) {
        this.id = id;
        this.subject = subject;
        this.status = status;
        this.createdAt = createdAt;
        this.customerId = customerId;
    }

    public Long getId() {
        return id;
    }

    public String getSubject() {
        return subject;
    }

    public String getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public Long getCustomerId() {
        return customerId;
    }
}