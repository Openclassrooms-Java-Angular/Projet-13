package com.jflament.yourcaryourway.dto;

import jakarta.validation.constraints.NotBlank;

public class CreateMessageRequest {

    @NotBlank
    private String content;

    private Long senderId;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getSenderId() {
        return senderId;
    }

    public void setSenderId(Long senderId) {
        this.senderId = senderId;
    }
}