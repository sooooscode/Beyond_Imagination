package com.beyondImagination.bookChatter.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ChatMessage {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private String sender; // 메시지 보낸 사람
  private String content; // 메시지 내용
  private boolean isVisible; // 메시지가 다른 사용자에게 보일지 여부
  private boolean isUserMessage; // 사용자 메시지인지 AI 메시지인지

  // 기본 생성자
  public ChatMessage() {
  }

  // 생성자
  public ChatMessage(Long id, String sender, String content, boolean isVisible, boolean isUserMessage) {
    this.id = id;
    this.sender = sender;
    this.content = content;
    this.isVisible = isVisible;
    this.isUserMessage = isUserMessage;
  }

  // Getter and Setter for id
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  // Getter and Setter for sender
  public String getSender() {
    return sender;
  }

  public void setSender(String sender) {
    this.sender = sender;
  }

  // Getter and Setter for content
  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
  }

  // Getter and Setter for isVisible
  public boolean isVisible() {
    return isVisible;
  }

  public void setVisible(boolean isVisible) {
    this.isVisible = isVisible;
  }

  // Getter and Setter for isUserMessage
  public boolean isUserMessage() {
    return isUserMessage;
  }

  public void setUserMessage(boolean isUserMessage) {
    this.isUserMessage = isUserMessage;
  }
}
