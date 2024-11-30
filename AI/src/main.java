public class Main {
    public static void main(String[] args) {
        String pdfPath = "/path/to/pdf";

        // RAGBookClubGPT 인스턴스 생성
        RAGBookClubGPT bookClub = new RAGBookClubGPT(pdfPath);

        // 사용자 응답 예시
        List<String> userResponses = List.of("응답1", "응답2", "응답3");

        // 발제문 생성
        bookClub.generateTopics(userResponses);

        // 주제별 응답 수집
        List<String> topics = List.of("주제1", "주제2");
        List<List<String>> allResponses = new ArrayList<>();
        for (String topic : topics) {
            allResponses.add(bookClub.collectResponses(topic));
        }

        // 자유 토론 시작
        bookClub.startFreeDiscussion();

        // 토론 요약
        bookClub.summarizeMeeting(topics, allResponses);
    }
}
