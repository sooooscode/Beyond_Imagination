# 패키지 설치
!pip install langchain==0.1.14 openai==1.14.3 langchain-openai==0.1.1
!pip install pypdf
!pip install langchain-chroma

# 모듈 임포트
from langchain.document_loaders import PyPDFLoader  # PDF 문서를 로드하기 위한 모듈
from langchain.embeddings.openai import OpenAIEmbeddings  # OpenAI 임베딩 모델 사용
from langchain.vectorstores import Chroma  # 벡터 저장소로 Chroma 사용
from langchain.chains import RetrievalQA  # 질의응답 체인을 위한 모듈
from langchain.chat_models import ChatOpenAI  # OpenAI의 채팅 모델 사용
import time  # 시간 관련 기능을 제공하는 모듈

# RAGBookClubGPT 클래스 정의
class RAGBookClubGPT:
    def __init__(self, pdf_path, api_key):
        """
        RAG 기반 독서 모임 AI 초기화
        pdf_path: 독서 모임 관련 PDF 문서 경로
        api_key: OpenAI API 키
        """

        # PDF 문서 로드
        loader = PyPDFLoader(pdf_path)
        self.docs = loader.load()

        # 벡터 임베딩 생성 및 데이터베이스 구축
        embeddings_model = OpenAIEmbeddings(api_key=api_key)
        self.db = Chroma.from_documents(self.docs, embeddings_model)

        # 데이터 검색기 및 QA 체인 초기화
        self.retriever = self.db.as_retriever()
        self.llm = ChatOpenAI(model_name="gpt-4", api_key=api_key)
        self.qa_chain = RetrievalQA.from_chain_type(
            llm=self.llm,
            chain_type="stuff",
            retriever=self.retriever
        )
    
    def generate_topics(self, user_responses):
        """
        RAG 기반으로 발제문 생성
        user_responses: 참가자 감상문
        """

        system_message = "Give me three topics in Korean that I can freely discuss about this book. They can't be political or hateful. They can't have set answers."
        user_message = "\n".join(user_responses)

        # 질의에 대해 RAG 기반 문맥 검색 및 발제문 생성
        context_docs = self.retriever.get_relevant_documents("발제문 생성에 필요한 주요 내용")
        context_text = "\n".join(doc.page_content for doc in context_docs)

        prompt = f"{system_message}\n\nPDF 문서 내용:\n{context_text}\n\n참가자 감상문:\n{user_message}"
        
        # GPT-4 모델을 사용하여 발제문을 생성
        response = self.llm.predict(prompt)

        topics = response.split("\n")  # 발제문 나누기
        print("선정된 발제문:")
        
        # 생성된 발제문을 출력
        for topic in topics:
            print(f"- {topic}")
        
        return topics

    def collect_responses(self, topic):
        """
        주어진 주제에 대해 참가자들의 의견을 수집하고, 욕설 및 비방 내용을 필터링하는 메서드
        """
        
        print(f'\n주제: {topic}')  # 현재 토론 주제를 출력
        print('이 주제에 대해 의견을 작성해 주세요.')  # 사용자에게 의견 작성 요청

        responses = []  # 사용자 응답을 저장할 리스트
        
        while True:
            user_input = input("사용자 입력 (형식: 사용자명: 의견, 종료 시 '종료' 입력): ").strip()
            
            if user_input.lower() == "종료":  # '종료' 입력 시 루프를 종료
                break
            
            try:
                user, response = user_input.split(":", 1)  # 사용자명과 의견을 분리
                responses.append(f"{user.strip()}: {response.strip()}")
            except ValueError:
                print("잘못된 입력 형식입니다. '사용자명: 의견' 형식으로 입력해주세요.")

        filtered_responses = self.filter_responses(responses)  # 필터링된 응답 처리

        print('필터링된 의견:')
        
        for response in filtered_responses:
            print(response)

        return filtered_responses

    def filter_responses(self, responses):
        """
        참가자들의 응답에서 욕설이나 비방이 포함된 내용을 필터링하는 메서드
        """
        
        bad_words = ['욕설', '비방']  # 필터링할 단어 목록 정의
        filtered = []  # 필터링된 응답을 저장할 리스트

        
        for response in responses:
            if not any(bad_word in response for bad_word in bad_words):  # 필터링할 단어가 포함되지 않은 경우만 추가
                filtered.append(response)

        
        return filtered

    def free_discussion(self):
        
         """
         자유 토론 시간을 제공하는 메서드. 실제로는 10분 동안 대기하지만, 테스트를 위해 짧게 설정됨.
         """
         
         
         print('이제 자유 토의 시간입니다. 10분 동안 의견을 나누세요.')

         time.sleep(1)  # 테스트용 대기 시간 (실제로는 time.sleep(600)으로 설정해야 함)
         
         print('자유 토의 시간 종료.')  

    def summarize_meeting(self, topics, all_responses):
        
         """
         RAG 기반으로 모임 내용을 요약하는 메서드
         topics: 논의된 주제들
         all_responses: 참가자들의 모든 응답
         """
         
         
         print('\n모임 내용을 요약합니다...')

         
         summary_prompt = "다음은 독서 모임에서 논의된 내용입니다. 이 내용을 바탕으로 간단한 요약을 작성해줘:\n"

         
         for i, topic in enumerate(topics):
             summary_prompt += f"주제 {i + 1}: {topic}\n"
             summary_prompt += "응답:\n" + "\n".join(all_responses[i]) + "\n"

         
         response = self.llm.predict(summary_prompt)  # GPT-4 모델을 사용하여 요약 생성

         
         summary = response.strip()
         print("모임 요약:")  
         print(summary)


# PDF 경로 및 OpenAI API 키 설정
pdf_path = "/content/현진건-운수좋은날+B3356-개벽.pdf"
api_key = "##API_key##"

# RAGBookClubGPT 인스턴스 생성
book_club_ai = RAGBookClubGPT(pdf_path, api_key)

# 참가자 감상문 리스트 정의
user_responses = [
    "현진건의 운수 좋은 날은 일제강점기 하층민의 비극적 삶을 그린 작품으로, 김첨지라는 인력거꾼의 하루를 통해 아이러니와 비극을 극대화한다. 김첨지는 아픈 아내의 만류에도 불구하고 일을 나가고, 그날따라 유난히 운이 좋아 많은 돈을 번다. 하지만 집에 돌아와서는 이미 싸늘한 시신이 된 아내를 발견하게 된다. 작품 제목에서부터 드러나는 반어적 표현은 독자에게 깊은 여운을 남긴다. 설렁탕을 사 왔지만 아내가 먹지 못하는 상황은 당시 하층민의 절망적인 현실을 상징적으로 보여준다. 이 작품은 단순한 개인의 비극을 넘어, 일제강점기라는 시대적 배경 속에서 민중이 겪어야 했던 고통과 무력함을 잘 드러낸다.",
    "운수 좋은 날은 겉으로는 운이 좋은 하루였지만, 결국에는 비극으로 끝나는 김첨지의 이야기를 통해 삶의 아이러니를 잘 보여준다. 김첨지는 그날따라 많은 돈을 벌어 기뻐하지만, 집에 돌아와 아내의 죽음을 맞닥뜨린다. 이 작품은 당시 사회적 현실과 개인의 비극이 어떻게 얽혀 있는지를 심도 있게 탐구한다. 특히, 김첨지가 아내를 위해 사 온 설렁탕이 결국 아무 의미 없게 되는 장면은 독자의 마음을 더욱 아프게 한다. 이러한 아이러니는 작품의 비극성을 한층 더 강화하며, 독자로 하여금 당시 시대 상황에 대한 깊은 성찰을 유도한다",
    "현진건의 운수 좋은 날은 일제강점기 하층민의 비극적 삶을 그린 작품으로, 김첨지라는 인력거꾼의 하루를 통해 아이러니와 비극을 극대화한다. 김첨지는 아픈 아내의 만류에도 불구하고 일을 나가고, 그날따라 유난히 운이 좋아 많은 돈을 번다. 하지만 집에 돌아와서는 이미 싸늘한 시신이 된 아내를 발견하게 된다. 작품 제목에서부터 드러나는 반어적 표현은 독자에게 깊은 여운을 남긴다. 설렁탕을 사 왔지만 아내가 먹지 못하는 상황은 당시 하층민의 절망적인 현실을 상징적으로 보여준다. 이 작품은 단순한 개인의 비극을 넘어, 일제강점기라는 시대적 배경 속에서 민중이 겪어야 했던 고통과 무력함을 잘 드러낸다."
]

# 발제문 생성 및 참가자 의견 수집 시작
topics = book_club_ai.generate_topics(user_responses)

# 각 주제별로 참가자 의견 수집 및 저장
all_responses = []
for topic in topics:
    responses = book_club_ai.collect_responses(topic)
    all_responses.append(responses)

# 자유 토론 진행
book_club_ai.free_discussion()

# 모임 내용을 요약하여 출력
book_club_ai.summarize_meeting(topics, all_responses)
