package com.beyondImagination.bookChatter.repository;

import com.beyondImagination.bookChatter.model.ChatMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChatMessageRepository extends JpaRepository<ChatMessage, Long> {
  // 필요한 추가 쿼리 메서드가 있다면 여기에
}
