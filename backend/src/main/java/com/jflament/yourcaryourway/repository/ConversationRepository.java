package com.jflament.yourcaryourway.repository;

import com.jflament.yourcaryourway.entity.Conversation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConversationRepository extends JpaRepository<Conversation, Long> {
    List<Conversation> findByCustomerIdOrderByCreatedAtDesc(Long customerId);
}