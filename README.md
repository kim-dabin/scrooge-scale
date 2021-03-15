# 스크루지 검사 Scrooge-scale

## 목적 Purpose

학부생일 때 '심리측정' 강의의 과제로 만든 심리 검사, <스크루지 척도>를 웹으로 구현하여 설문 작성 후 제출하면 실시간으로 통계치를 구하여 해당 유저의 구두쇠 성향 정도를 알게 한다.  

GCP(Google Cloud Platform)를 통해 데이터 파이프라인 서버를 구성했고 Hadoop Eco-system을 이용해 데이터를 ETL(추출, 변환, 적재) 하였다.

<img src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/f745106a-b571-462f-bfde-f15cb0e09d71/fronMain.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210315%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210315T082852Z&X-Amz-Expires=86400&X-Amz-Signature=1ea428a409fe80a797ae834ed3ed92c59bbe9f3076e6ac1dd10c3e8688d59ec1&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22fronMain.png%22" style="zoom: 20%;" />

<img src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/2c8bc9b1-5840-491f-8377-66ace2320847/front2.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210315%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210315T082916Z&X-Amz-Expires=86400&X-Amz-Signature=c4636aa601e9bf47b29d593f4f097d181471aacbb530543af1436dcff767f92f&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22front2.png%22" style="zoom:20%;" />

<img src="https://s3.us-west-2.amazonaws.com/secure.notion-static.com/396ec116-19d8-4271-ad04-ed953341b243/Untitled.png?X-Amz-Algorithm=AWS4-HMAC-SHA256&X-Amz-Credential=AKIAT73L2G45O3KS52Y5%2F20210315%2Fus-west-2%2Fs3%2Faws4_request&X-Amz-Date=20210315T082918Z&X-Amz-Expires=86400&X-Amz-Signature=2f552a5b6bd1ac8941622df11ab8e2456ba18bae98d11aa0f718f0bbc856da06&X-Amz-SignedHeaders=host&response-content-disposition=filename%20%3D%22Untitled.png%22" style="zoom:20%;" />

## 스크루지 검사 Scrooge-scale

- 돈을 소비하는 것에 있어서 두려워하고 소비하지 않는 것에 대해 집착하는 사람을 선별해 낼 수 있는 질문지
- 구두쇠 성향을 측정
- 리커트 7척도를 이용해 채점

### 질문지 개발 내용

- 구두쇠의 유전적 영향 연구 문헌, 생활 관찰 및 관련 질문지들을 통해 재산의 정도와 차이 없이 자신 또는 타인에게 돈을 쓰는 것에 인색한 사람들의 특성을 잘 반영해주는 문항들을 표집
- 안면타당도에 대한 평정을 거쳐 예비 질문지를 구성
- 예비 질문지를 92명의 대학생들에게 실시 후 요인분석을 한 다음 12문항을 선정 
- 본문항은 총 219명의 미혼인 20대 대학생에게 표집을 실시하여 요인분석 후 중복되는 문항을 제외
- 최종적으로 11문항을 선정해 ‘스크루지 척도’를 개발
- 요인분석 결과, 스크루지 질문지는 소비에 따른 현재성 결여, 소비 시 정서 상태, 경직된 소비 태도의 3요인 구조로 이루어짐
- Yoon-Mi Hur(2011)의 "Miserliness is heritable(구두쇠적인 것은 유전이다)"질문지 및 김효정의 "과소비 대학생 소비자의 충 동구매 행동" 질문지를 통해 수렴, 변별 타당도를 살펴봄

### 문항

1. 나는 돈을 사용할 때 마음이 편하다.
2. 나는 돈에 대한 생각 때문에 현재의 일에 충실하지 못하다.
3. 나는 돈을 사용할 때 마음이 든든하다.
4. 나는 아직 생기지도 않은 금전적 피해를 걱정한다.
5. 나는 돈을 사용할 때 마음이 차분하다.
6. 나는 돈에 대한 생각 때문에 나에게 주어진 일에 전념하지 못한다.
7. 나는 내게 필요성이 있을 것이라고 확신이 들지 않는다면 돈을 쓰지 않는다.
8. (돈 때문에) 과제나 업무를 수행할 때 정신을 집중하기 힘들다.
9. 나는 다른 대체물이 있다고 예상되는 것에 돈을 쓰지 않는다.
10. 나는 돈을 사용할 때 즐겁다.
11. 나는 내게 이득이 있을 것이라고 확신이 들지 않으면 돈을 쓰지 않는다.



## 사용 기술 Technology Choices

- Java8
- GCP Compute Engine
- CentOS 7
- Springboot
- Apach Hadoop 2.X
- Apach Hbase 2.X
- Apach Zookeeper
- Apach Kafka
- Apach Spark
- Redis
- Mariadb



## 아키텍처 Architecture

![](https://www.notion.so/image/https%3A%2F%2Fs3.us-west-2.amazonaws.com%2Fsecure.notion-static.com%2Fc61b216f-0c38-429b-b01b-b7bce933907f%2FUntitled.png%3FX-Amz-Algorithm%3DAWS4-HMAC-SHA256%26X-Amz-Credential%3DAKIAT73L2G45O3KS52Y5%252F20210312%252Fus-west-2%252Fs3%252Faws4_request%26X-Amz-Date%3D20210312T121114Z%26X-Amz-Expires%3D86400%26X-Amz-Signature%3D7a259ce0f9b2bab0f2de248347e7744371bce6a22e35be8124a89a71117a0103%26X-Amz-SignedHeaders%3Dhost%26response-content-disposition%3Dfilename%2520%253D%2522Untitled.png%2522?table=block&id=d6c31669-889f-4176-a9d7-fe654be934b0&width=2390&userId=&cache=v2)





