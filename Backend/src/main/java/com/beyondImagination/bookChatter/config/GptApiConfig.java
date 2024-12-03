package com.beyondImagination.bookChatter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GptApiConfig {

  @Value("${gpt.api.key}") // application.properties에서 gpt.api.key 값을 가져옴
  private String apiKey;

  public String getApiKey() {
    return apiKey;
  }
}