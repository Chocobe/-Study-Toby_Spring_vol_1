# 토비의 스프링 1권

> ### 1장. [오브젝트와 의존관계](https://github.com/Chocobe/-Study-Toby_Spring_vol_1/blob/master/1%EC%9E%A5.%20%EC%98%A4%EB%B8%8C%EC%A0%9D%ED%8A%B8%EC%99%80%20%EC%9D%98%EC%A1%B4%EA%B4%80%EA%B3%84.md)

> ### 2장. [테스트](https://github.com/Chocobe/-Study-Toby_Spring_vol_1/blob/master/2%EC%9E%A5_%ED%85%8C%EC%8A%A4%ED%8A%B8.md)

> ### 3장. [템플릿](https://github.com/Chocobe/-Study-Toby_Spring_vol_1/blob/master/3%EC%9E%A5_%ED%85%9C%ED%94%8C%EB%A6%BF.md)

> ### 4장. [예외처리](https://github.com/Chocobe/-Study-Toby_Spring_vol_1/blob/master/4%EC%9E%A5_%EC%98%88%EC%99%B8%EC%B2%98%EB%A6%AC.md)

> ### 5장. [서비스 추상화](https://github.com/Chocobe/-Study-Toby_Spring_vol_1/blob/master/5%EC%9E%A5_%EC%84%9C%EB%B9%84%EC%8A%A4%EC%B6%94%EC%83%81%ED%99%94.md)

> ### 6장. [AOP](https://github.com/Chocobe/-Study-Toby_Spring_vol_1/blob/master/6%EC%9E%A5_AOP.md)

> ### 참고 : [Maven의존성]()

---


## 단축키

> 1. 드래그 영역 다음 찾기 : Ctrl + k

> 1. 드래그 영역 이전 찾기 : Ctrl + Shift + k


## 실습 흐름

### 1장. 오브젝트와 의존관계

> 1. DAO를 이용한 CRUD 작성
>
> 1. XML 설정파일을 이용하는 **애플리케이션 컨텍스트** 사용
>


### 2장. 테스트

> 1. JUnit의 **@Test** 작성
>
> 1. **스프링 테스트 컨텍스트 프레임워크** 사용
>
> 1. **CRUD** 기능 **@Test** 하기
>
> 1. **픽스처**의 특징을 **@Test** 하기


### 3장. 템플릿

> 1. DAO 클래스에서 **비중복**을 메서드로 분리하기
>
> 1. **비중복** 부분을 인터페이스를 통한 **내부 익명 클래스**로 사용하기
>
> 1. DAO 클래스에서 **중복** 부분을 메서드로 분리하기
>
> 1. **중복** 부분을 **컨텍스트** 역할의 클래스로 추출하기
>
> * 위의 작업을 통한 **탬플릿/콜백** 패턴을 만든 것
>
>   1. **스프링 빈** 을 이용한 DI
>
>   1. **수동 DI** 를 이용한 DI
>
>   1. [text파일 읽어오기 예제](https://github.com/Chocobe/-Study-Toby_Spring_vol_1/tree/master/toby_vol_1/fileReaderTest/src/fileReaderTest)


### 4장. 예외처리

> 1. 스프링의 상위 예외 클래스인 DataAccessException에 대한 이해
>
> 1. 발생한 예외의 구체적인 예외 클래스 확인
>
> 1. 복구할 수 없는 예외를 **RuntimeException**으로 포장하기


### 5장. 서비스 추상화

> 1. 트랜잭션 설정하기
>
> 1. 트랜잭션과 비지니스 로직 분리하기
>
> 1. 테스트 대역 - **테스트 스텁(test stub)**, **목 오브젝트(mock)**


### 6장. AOP

> 1. Mockito : 목 오브젝트 지원 프레임워크 소개

> 1. 프록시 개념