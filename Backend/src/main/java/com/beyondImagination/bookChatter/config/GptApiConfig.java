package com.beyondImagination.bookChatter.config;

<<<<<<< HEAD
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "gpt.api")
public class GptApiConfig {

  private String key;

  public String getKey() {
    return key;
  }

  public void setKey(String key) {
    this.key = key;
=======
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GptApiConfig {

  @Value("${gpt.api.key}") // application.properties에서 gpt.api.key 값을 가져옴
  private String apiKey;

  public String getApiKey() {
    return apiKey;
>>>>>>> 38287ecc (최종 오류 해결 및 빌드 성공: 중복된 org.springframework:spring-context를 삭제함, gpt.api key를 추가함)
  }
}