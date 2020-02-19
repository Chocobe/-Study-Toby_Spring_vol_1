# 토비의 스프링 1권

# 1장. 오브젝트와 의존관계




## 🐫 리펙토링 하기

> 관심사항 분리하기
	

### 1. 중복코드의 메서드 추출
            
    다른 관심사와 분리하여, 변경에 대한 영향력을 줄일 수 있다.
            

### 2. 상속을 통한 확장 (추상 클래스로 만들기)
            
* **템플릿 메서드 패턴** : 슈퍼클래스에 기본적인 로직의 흐름을 만들고, 그 기능의 일부를 추상 메서드로 만드는 방법

    템플릿 메서드 패턴에서 추상 메서드를 훅(hook) 메서드라고 한다.
            
* **팩토리 메서드 패턴** : 서브 클래스에서 구체적인 오브젝트 생성 방법을 결정하게 하는 방법


### 3. 인터페이스를 사용한 책임 분리

    객체 생성의 책임을 외부로 위임함으로써 강한 결합을 해소할 수 있다.


### 4. 전략 패턴

    변경이 자주 생기는 알고리즘을 인터페이스의 추상 메서드로 만듦으로써 필요에 따라 바꿔서 사용할 수 있게 하는 디자인 패턴

    즉, 전략을 바꿔가며 사용한다는 의미 (해당 전략은 생성자의 인자로 지정해 주는게 일반적)




## 🐫 원칙과 패턴

> 유연한 설계를 위한 원칙과 패턴


### 1. 단일 책임 원칙

    메서드, 클래스, 컴포넌트는 한가지 기능만 수행하도록 만들어야 한다.


### 2. 개방 폐쇄 원칙

    클래스나 모듈은 **확장**에는 열려 있어야 하고, **변경**에는 닫혀 있어야 한다.


### 3. 리스코프 치환 원칙

    상속관계 시, 서브타입은 기반타입으로 교체할 수 있어야만 한다.

        예) 
        
        사각형 클래스는 가로, 세로 변수값을 가진다.

        정사각형 클래스는 길이 변수값 하나만 가진다.

        그러므로, 정사각형 클래스가 사각형 클래스를 상속받으면, 리스코프 치환 원칙을 위반하게 된다.

    즉, "서브 클래스 is 기반 클래스" 를 지키자는 의미 (상속관계 원칙)


### 4. 인터페이스 분리 원칙

    클라이언트는 자신이 사용하지 않는 메서드에는 의존관계를 맺으면 안된다.


### 5. 의존관계 역전 원칙

    의존관계를 맺을 때, 변하기 쉬운 것 보다 "변하기 어려운 것에 의존하라"는 원칙이다. 




## 🐫 제어의 역전(IoC)

    제어권을 상위 템플릿 메서드에 넘기고, 

    자신은 클라이언트가 필요에 따라 호출하여 사용되도록 하는 개념이다.

    "템플릿 메서드 패턴"의 훅(hook)메서드가 제어의 역전을 활요한 예 이다.

    제어의 역전이 적용된 클래스를 "스프링빈" 또는 "빈" 이라고 한다.


* "빈"을 생성하는 클래스를 **빈팩토리** 또는 **애플리케이션 컨텍스트** 라고 한다.

    1. 스프링의 설정파일 역할을 한다. (== 애플리케이션의 설계도)

    1. **빈팩토리** == **애플리케이션 컨텍스트**

        * **빈팩토리** 라고 부를 때 : "빈"객체를 생성하여 반환하는 기능에 초점을 둔 명칭

        * **애플리케이션 컨텍스트** 라고 부를 때 : "설정파일" 역할에 초점을 둔 명칭


* 필요한 스프링 라이브러리

    1. com.springsource.net.sf.cglib-2.2.0.jar

    1. com.springsource.org.apache.commons.logging-1.1.1.jar

    1. org.springframework.asm-3.0.7.RELEASE.jar

    1. org.springframework.beans-3.0.7.RELEASE.jar

    1. org.springframework.context-3.0.7.RELEASE.jar

    1. org.springframework.core-3.0.7.RELEASE.jar

    1. org.springframework.expression-3.0.7.RELEASE.jar


* **빈팩토리** 또는 **애플리케이션 컨텍스트** 만들기

    1. (import org.springframework.context.annotation.Configuration) - 클래스에 **@Configuration** 지정

    1. (import org.springframework.context.annotation.Bean;) - **빈**을 생성하는 **메서드**에 **@Bean** 지정


* **빈팩토리** 또는 **애플리케이션 컨텍스트** 사용하기
    
    1. (import org.springframework.context.ApplicationContext) - **ApplicationContext** 타입 객체 생성하기

    ```java
    ApplicationContext context = new AnnotationConfigApplicationContext(/* 빈팩토리_클래스명.class */);
    /* 빈의 클래스 타입 */ 객체명 = context.getBean("빈 메서드명", /* 반환타입.class */);
    ```



## 🐫 스프링 IoC 용어

* 빈(Bean)

    스프링이 IoC방식으로 관리하는 오브젝트 라는 뜻.

    스프링이 직접 그 생성과 제어를 담당하는 오브젝트만을 빈(Bean) 이라고 한다.


* 빈 팩토리(Bean Factory)

    스프링의 IoC를 담당하는 핵심 컨테이너를 말한다.

    일반적으로는 빈 팩토리(Bean Factory)를 사용하지는 않고, 이를 확장한 애플리케이션 컨텍스트를 사용한다.

    빈 팩토리(Bean Factory) 인터페이스에는 "getBean()"과 같은 메서드가 선언되어 있다.


* 애플리케이션 컨텍스트(Application Context)

    빈 팩토리(Bean Factory)를 확장한 IoC 컨테이너 이다.

    빈 팩토리(Bean Factory)를 상속한다.


* 설정정보 / 설정 메타정보 (Configuration Metadata)

    애플리케이션 컨텍스트 또는 빈 펙토리가 IoC를 적용하기 위해 사용하는 메타정보를 말한다.
    
    컨테이너의 기능을 세팅/조정할 때 사용한다.

    주로 IoC 컨테이너에 의해 관리되는 애플리케이션 오브젝트(빈(Bean))을 생성하고 구성할 때 사용한다.


* 컨테이너 또는 IoC컨테이너

    "IoC방식으로 빈을 관리한다"는 의미에서 애플리케이션 컨텍스트나 빈 팩토리를 "컨테이너" 또는 "IoC컨테이너"라고 부른다.

    **스프링에 빈을 등록하고...** 라는 말의 **스프링**은 **스프링 컨테이너** 또는 **애플리케이션 컨텍스트**를 가리키는 말이다.




## 🐫 싱글톤 레지스트리 로써의 애플리케이션 컨테이너

* 동일한 타입의 객체를 다수 생성하게 되면, 동등한 객체이긴 하지만 같은 객체는 아니다.

* 객체를 매번 생성하는 것이 아니라 하나의 객체로만 사용하는 방식을 **싱글톤 패터** 이라고 한다.

* 애플리케이션 컨텍스트의 **getBean()**으로 반환되는 객체는 모두 동일한 객체이다. (싱글톤 패턴과 같은 결과)

* 기존 디자인 패턴에서의 싱글톤과 스프링의 싱글톤 레지스트리는 다르다.

    기존 디자인 패턴에서는 생성자를 **private**으로 만들기 때문에 테스트가 어렵거나 불가능하고, 객체지향의 목적에 위배된다.

    반면, 애플리케이션 컨텍스트에서는 애플리케이션 컨텍스트가 객체 생성을 전담하기 때문에, 싱글톤과 같은 효과를 가진다.

* 즉, 애플리케이션 컨텍스트는 **싱글톤 형태의 객체를 만들고 관리하는 기능**을 제공한다.

* 싱글톤 객체의 **인스턴스 변수**는 동시에 여러곳에서 사용할 시, 서로 값을 덮어씌우는 심각한 문제를 발생 시킨다.

* 싱글톤 객체의 **인스턴스 변수**가 읽기 전용이라면, 사용해도 좋다.

    자신(this)이 사용하는 다른 싱글톤 빈을 저장하려는 용도일 때, 인스턴스 변수를 사용해도 좋다.

    단순히 **읽기전용 값** 이라면, **static final** 또는 **final**로 선언하는 것이 더 좋다.

* 애플리케이션 컨텍스트의 빈(Bean)을 만들경우, 바뀌는 데이터는 **로컬변수** 또는 **파라메터**로 사용해야 한다.

    로컬변수나 파라메터는 호출시 마다 **새로운 공간**이 만들어지기 때문에 문제가 발생하지 않는다.




## 🐫 스프링 빈의 스코프

* 스프링에서 만들어지는 대부분의 빈은 **싱글톤 스코프**를 갖는다. (객체 하나만으로 계속 사용)

* 경우에 따라서, prototype스코프, request스코프, session스코프 등 다양하다.