```
book-club-ai/
│
├── src/
│   ├── RAGBookClubGPT.java        # 주요 로직
│   ├── PDFLoader.java             # PDF 로딩 및 처리
│   ├── TextProcessor.java         # 텍스트 처리 및 필터링
│   ├── ResponseFilter.java        # 응답 필터링
│   ├── DiscussionManager.java     # 토론 관리
│   └── Main.java                  # 프로그램 실행
│
├── lib/
│   └── external-libraries.jar     # 외부 라이브러리 (예: OpenAI API 연결)
│
└── README.md                     # 프로젝트 설명
```

</br>
RAGBookClubGPT.java:
RAGBookClubGPT 클래스의 주요 로직을 포함합니다. 이 클래스는 PDF 로딩, 응답 수집, 주제 생성, 자유 토론, 요약 생성 등을 담당합니다

PDFLoader.java:
PDF 파일을 로드하는 로직을 이 클래스에서 관리합니다. PyPDFLoader와 비슷한 역할을 합니다.

TextProcessor.java:
텍스트 데이터 처리 관련 함수들을 포함합니다. 예를 들어, 감상문 수집 후 텍스트를 결합하거나, 토픽 생성에 필요한 컨텍스트를 준비하는 로직이 포함될 수 있습니다.

ResponseFilter.java:
응답에서 욕설과 비방을 필터링하는 로직을 포함합니다. bad_words 목록을 기반으로 필터링 작업을 합니다.

DiscussionManager.java:
토론 관리, 예를 들어, 각 주제에 대해 참가자들의 응답을 수집하거나, 자유 토론을 진행하는 로직을 담당합니다.

Main.java:
프로그램의 엔트리 포인트. 이 파일에서 다른 클래스를 호출하여 프로그램을 실행합니다. 각 기능을 테스트하거나 실행하는데 필요한 모든 구성을 여기서 합니다.
