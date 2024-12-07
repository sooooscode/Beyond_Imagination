package com.beyondImagination.bookChatter.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GptApiConfig {

  @Value("${gpt.api.key}")
  private String apiKey;

  public String getApiKey() {
    return apiKey;
  }
}