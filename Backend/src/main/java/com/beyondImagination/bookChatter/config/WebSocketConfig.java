package com.beyondImagination.bookChatter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

  // application.properties에서 spring.websocket.enabled 속성 값을 읽어옵니다.
  @Value("${spring.websocket.enabled}")
  private boolean websocketEnabled;

  // application.properties에서 gpt.api.key 값을 읽어옵니다.
  @Value("${gpt.api.key}")
  private String gptApiKey;

  // 메시지 브로커를 설정합니다.
  @Override
  public void configureMessageBroker(MessageBrokerRegistry config) {
    // websocket이 활성화되어 있으면 설정을 진행합니다.
    if (websocketEnabled) {
      // 메시지 브로커 설정
      config.enableSimpleBroker("/topic", "/queue"); // /topic, /queue에 대한 메시지 브로커 설정
      config.setApplicationDestinationPrefixes("/app"); // 클라이언트에서 보내는 메시지의 목적지 접두어 설정
    } else {
      // WebSocket 비활성화 시, 메시지 처리 또는 오류 처리 로직 추가 가능
      System.out.println("WebSocket is disabled in application.properties.");
    }
  }

  // WebSocket STOMP 엔드포인트 설정
  @Override
  public void registerStompEndpoints(StompEndpointRegistry registry) {
    // websocket이 활성화된 경우에만 /chat 엔드포인트를 추가합니다.
    if (websocketEnabled) {
      registry.addEndpoint("/chat").withSockJS(); // SockJS 지원
    }
  }

  // GPT API 키를 사용할 수 있도록 메서드 추가 (예시)
  public String getGptApiKey() {
    return gptApiKey;
  }
}
