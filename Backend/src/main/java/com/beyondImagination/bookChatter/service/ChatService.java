package com.beyondImagination.bookChatter.service;

import com.beyondImagination.bookChatter.model.ChatMessage;
import com.beyondImagination.bookChatter.repository.ChatMessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

@Service
public class ChatService {

  @Autowired
  private ChatMessageRepository chatMessageRepository;

  // 메시지 저장
  public void saveMessage(ChatMessage message) {
    chatMessageRepository.save(message);
  }

  // 모든 메시지를 랜덤한 순서로 가져와서 반환하는 메서드
  public List<ChatMessage> getMessagesInRandomOrder() {
    List<ChatMessage> messages = chatMessageRepository.findAll(); // 모든 메시지를 가져옵니다.
    Collections.shuffle(messages); // 리스트를 랜덤 순서로 섞습니다.
    return messages;
  }

  // 특정 사용자에 대한 메시지를 랜덤 순서로 전송하는 메서드 구현
  public void sendMessagesRandomly(List<ChatMessage> messages) {
    Collections.shuffle(messages); // 메시지 순서를 섞어 랜덤화
    for (ChatMessage message : messages) {
      // 메시지를 전송하는 로직을 여기에 추가합니다.
      // 예를 들어, 전송 API 호출, 다른 서비스로 전송, 로그 출력 등
      System.out.println("전송된 메시지: " + message.getContent()); // 메시지 출력 (예시)
    }
  }
}
