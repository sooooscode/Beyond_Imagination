
package com.beyondImagination.bookChatter.service;

import java.io.*;
import java.net.*;
import java.util.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

//soo추가: Service 애노테이션 추가
@Service
public class BookClubGPT {
  private List<String> topics;
  private List<String> participantsResponses;

  // soo추가:
  @Value("${gpt.api.key}") // application.properties에서 gpt.api.key 값을 읽어옴
  private String apiKey;

  public BookClubGPT(List<String> participantsResponses) {
    this.topics = new ArrayList<>();
    this.participantsResponses = participantsResponses;
  }

  // 발제문 생성
  public void generateTopics() {
    String systemMessage = "너는 독서모임의 진행자야. 사용자들이 제출한 감상문을 바탕으로 발제문 3개를 선정해줘. 찬반이 확실히 갈리지 않고 자유롭게 의견을 나눌 수 있는 주제로 선정해줘.";
    String userMessage = String.join("\n", participantsResponses);

    try {
      String response = callGPT4API(systemMessage, userMessage);
      // API 응답에서 발제문 추출 (줄바꿈으로 구분된 발제문 리스트)
      String[] topicsArray = response.split("\n");
      topics = Arrays.asList(topicsArray);

      System.out.println("선정된 발제문:");
      for (String topic : topics) {
        System.out.println("- " + topic);
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // 의견 수집
  public List<String> collectResponses(String topic) {
    System.out.println("\n주제: " + topic);
    System.out.println("이 주제에 대해 의견을 7분 동안 작성해 주세요.");

    // 예시 사용자 응답 수집 (실제로는 사용자 입력이 필요함)
    List<String> responses = Arrays.asList(
        "사용자1: 1984는 전체주의의 위험성을 잘 보여주는 작품입니다.",
        "사용자2: 이 소설은 너무 비관적이에요. 현실과는 거리가 멀다고 생각합니다.",
        "사용자3: 욕설을 포함한 의견...", // 욕설 포함된 의견 예시
        "사용자4: 이 작품은 인간의 자유와 권리를 생각하게 해줘요.");

    // 필터링된 응답 처리 (욕설 및 비방 내용 제거)
    List<String> filteredResponses = filterResponses(responses);

    System.out.println("필터링된 의견:");
    for (String response : filteredResponses) {
      System.out.println(response);
    }

    return filteredResponses;
  }

  // 필터링
  private List<String> filterResponses(List<String> responses) {
    List<String> badWords = Arrays.asList("욕설", "비방");
    List<String> filtered = new ArrayList<>();

    for (String response : responses) {
      boolean containsBadWord = false;
      for (String badWord : badWords) {
        if (response.contains(badWord)) {
          containsBadWord = true;
          break;
        }
      }
      if (!containsBadWord) {
        filtered.add(response);
      }
    }

    return filtered;
  }

  // 자유토론
  public void freeDiscussion() {
    System.out.println("이제 자유 토의 시간입니다. 10분 동안 의견을 나누세요.");

    try {
      // 실제로는 10분 대기 (time.sleep(600)) 해야 하지만, 테스트용으로 짧게 설정
      Thread.sleep(1000); // 테스트용 대기 시간 (1초)
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    System.out.println("자유 토의 시간 종료.");
  }

  // 모임 내용 요약
  public void summarizeMeeting() {
    System.out.println("\n모임 내용을 요약합니다...");

    String summaryPrompt = "다음은 독서 모임에서 논의된 내용입니다. 이 내용을 바탕으로 간단한 요약을 작성해줘:\n";
    for (String response : participantsResponses) {
      summaryPrompt += response + "\n";
    }

    try {
      String summary = callGPT4API("너는 독서모임의 진행자야. 독서모임에서 논의된 내용을 요약해줘.", summaryPrompt);
      System.out.println("모임 요약:");
      System.out.println(summary);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  // GPT-4 API 호출
  private String callGPT4API(String systemMessage, String userMessage) throws IOException {
    String apiKey = ""; // API key 넣기
    URL url = new URL("https://api.openai.com/v1/chat/completions");

    HttpURLConnection connection = (HttpURLConnection) url.openConnection();
    connection.setRequestMethod("POST");
    connection.setRequestProperty("Content-Type", "application/json");
    connection.setRequestProperty("Authorization", "Bearer " + apiKey);
    connection.setDoOutput(true);

    String jsonPayload = "{\n" +
        "  \"model\": \"gpt-4\",\n" +
        "  \"messages\": [\n" +
        "    {\"role\": \"system\", \"content\": \"" + systemMessage + "\"},\n" +
        "    {\"role\": \"user\", \"content\": \"" + userMessage + "\"}\n" +
        "  ]\n" +
        "}";

    try (OutputStream os = connection.getOutputStream()) {
      byte[] input = jsonPayload.getBytes("utf-8");
      os.write(input, 0, input.length);
    }

    try (BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "utf-8"))) {
      StringBuilder response = new StringBuilder();
      String responseLine;
      while ((responseLine = br.readLine()) != null) {
        response.append(responseLine.trim());
      }
      return response.toString(); // 응답 내용 반환
    }
  }

  // 테스트 실행
  public static void main(String[] args) {
    List<String> participantsResponses = Arrays.asList(
        "참여자 1: 조지 오웰의 1984는 전체주의 체제의 위험성을 강력하게 경고하는 작품이다...",
        "참여자 2: 1984에서 당은 진실과 정보를 철저히 통제하며, 이를 통해 사회를 지배한다...",
        "참여자 3: 소설 속 오세아니아 사회에서는 개인의 정체성이 철저히 억압된다...",
        "참여자 4: 1984에서 사랑과 인간성은 가장 강력한 저항의 형태로 그려진다...");

    BookClubGPT bookClubGPT = new BookClubGPT(participantsResponses);
    bookClubGPT.generateTopics();

    for (String topic : bookClubGPT.topics) {
      List<String> responses = bookClubGPT.collectResponses(topic);
      bookClubGPT.freeDiscussion();
    }

    bookClubGPT.summarizeMeeting();
  }
}
