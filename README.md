# JAVA 성적 입력 및 출력 프로그램

* 작성일자 : 2020.07.13.Mon

* 사용 언어 : Java 1.8
* 사용 도구 : Eclipse IDE

* ScoreVO
  - 필드변수 선언
  - 학번, 과목(국어, 영어, 수학, 음악), 총점, 평균
  - 접근제한자 private으로 변수 선언 후 getter, setter 생성

* ScoreServiceV1
  - loadScore() : 데이터가 저장된 score.txt 파일이 존재한다면 불러오기
  - scoreInput() : 키보드를 통해 과목별 점수 입력받기
  - scoreSave() : 입력된 점수를 score.txt 파일에 저장
  - calcSum(), calcAvg() : 저장된 데이터로 총점과 평균 별도 계산
  - scoreList() : 입력된 내용을 종합하여 리스트 작성

 * ScoreEx_01
  - main() : ScoreServiceV1을 호출하여 Console에 성적일람표 출력
