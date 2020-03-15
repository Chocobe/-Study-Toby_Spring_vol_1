# 6장. AOP




## 🐫 태스트 대상 고립시키기

* 객체들은 서로 의존관계를 갖고있기 때문에 상호작용에 의해 의존하는 모든 객체가 테스트에 참여하게 된다.

* 다양한 객체들을 한번에 테스트하는 것은 만들기도 어렵고, 이해하기도 어렵기 때문에 테스트 대상만 따로 고립시켜 테스트해야 한다.

* 테스트 대상을 고립시키는 방법은 **테스트 스텁(test stub)** 과 **목 오브젝트(mock object)** 를 이용하는 것이다.

* 테스트 대상이 의존하는 객체들을 용도에 맞게 **테스트 스텁(test stub)** 과 **목 오브젝트(mock object)** 로 재구성 하는 것이다.

* 단지 테스트를 위한 보조라면 **테스트 스텁(test stub)**으로 만든다.

* 테스트의 과정이나 void타입의 메서드를 호출할 경우에는 **목 오브젝트(mock object)** 로 만든다.

* 테스트용 객체(테스트 유닛)은 테스트에 필요한 메서드만 테스트용으로 구현하고, 이외의 메서드는 다음과 같이 작성한다.

    ```java
        @Override public add(User user) { throw new UnsupportedOperationException(); }
    ```


---


## 🐫 Mockito (목 오브젝트 지원 프레임워크)

* mock오브젝트를 생성해 주는 프레임워크

> ### TDD관련 서적을 통해 공부해 두자.


---


## 🐫 프록시(Proxy)

* 프록시란, 클라이언트가 사용하려는 실제 대상인 것처럼 위장하여, 클라이언트의 요청을 대신 받아주는 객체를 말한다.

* 대리인 역할의 **프록시**, 실제 대상인 **타겟** 으로 구성되어 동작한다.

* 프록시는 타겟과 **동일한 인터페이스**를 **구현**한다.

* 프록시는 다음과 같이 두가지로 구분할 수 있다.

    1. 타겟에 부가기능을 부여해주기 위한 프록시

    1. 클라이언트가 타겟에 접근하는 방법을 제어하기 위한 프록시

* 프록시는 기존 코드(타겟)에 영향을 주지 않으면서, 타겟의 긴릉을 확장, 접근제어를 할 수 있는 방법이다.

---


> ### 프록시의 데코레이터 패턴

* 타겟에 대가기능을 부여해주는 패턴

* 형식은 다음과 같다.

    * 클라이언트 -> 데코레이터 패턴 -> 데코레이터 패턴 -> ... -> 타겟


> ### 프록시의 프록시 패턴

* 타겟에 접근하는 방법을 제어해주는 패턴


> ### 데코레이터 패턴과 프록시 패턴의 혼합 사용 예

* 클라이언트 -> 프록시 패턴 -> 데코레이터 패턴 -> 데코레이터 패턴 -> ... -> 타겟


---


## 🐫 Reflection (리플렉션)

* **다이나믹 프록시** 는 프록시를 만들어줄 때, **리플렉션**을 이용한다.

* 리플렉션의 이점은, 메서드를 수행하는 실제 객체를 몰라도, 런타임 시점에서 실제 객체를 지정하여 메서드를 호출할 수 있다는 것이다.

* 먼저 타입과 메서드만 추출해 놓고, 메서드를 사용할 때는 실제 동작할 객체명을 인자로 하여, 인자 객체가 추출된 메서드를 수행하는 방법

* 메서드는 **클래스명.class.getMethod("메서드명");** 형식으로 추출할 수 있다.

* 추출한 메서드는 **java.lang.reflect.Method** 타입으로 보관할 수 있다.

* 추출한 메서드를 실제 사용할 떄는 **Method변수.invoke("객체명", 인자타입);** 으로 호출할 수 있다.

* 사용법

    ```java
        MyClass myClass = new MyClass();

        Method method = MyClass.class.getMethod("메서드명", int.class, String.class);
        method.invoke("myClass", 3, "hello");
    ```


---


## 🐫 다이나믹 프록시

* java.lang.reflect.Proxy 를 사용하여 프록시 객체를 생성할 수 있다.

* 프록시 객체는 다음과 같이 생성할 수 있다.

    ```java
        Hello proxiedHello = (대상 인터페이스)Proxy.newProxyInstance(getClass().getClassLoader(),
                    new Class[] {대상 인터페이스},
                    new InvocationHandler("타겟 객체"));
    ```

* 프록시 객체를 통한 메서드 호출

    ```java
        proxiedHello.대상_인터페이스_메서드();
    ```

* 다이나믹 프록시는 호출되는 모든 메서드를 **InvocationHandler 객체**의 **invoke()** 메서드 호출로 수행한다.

* InvocationHandler 클래스 작성하기

    ```java
        class MyInvocationHandler implements InvocationHandler {
            private 대상_인터페이스 target;

            public MyInvocationHandler(대상_인터페이스 target) {
                this.target = target;
            }

            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                // 선행작업

                Object result = method.invoke(target, args);
                
                // 후행작업

                return result;
            }
        }
    ```


---


## 🐫 팩토리 빈

* **팩토리 빈** 이란, 스프링을 대신해서 오브젝트의 **생성로직을 담당**해주는 특변한 **빈(Bean)**이다.

* **다이나믹 프록시**를 생성할 때 사용한다.

* **팩토리 빈**은 **FactoryBean<?>** 인터페이스를 구현하여, **빈(Bean)** 으로 등록해주면, 스프링 대신 객체를 생성해 준다.

* 팩토리빈 구현

    ```java
        // 팩토리빈 으로 생성할 대상 클래스
        public class Message {
            private String text;


            private Message(String text) {
                this.text = text;
            }


            public String getText() {
                return text;
            }


            public static Message newMessage(String text) {
                return new Message(text);
            }
        }
    ```

    ```java
        // 팩토리빈 구현
        public class MessageFactoryBean implements FactoryBean<Message> {
            private String text;


            public void setText(String text) {
                this.text = text;
            }


            @Override
            public Message getObject() throws Exception {
                return Message.newMessage(this.text);
            }


            @Override
            public Class<? extends Message> getObjectType() {
                return Message.class;
            }


            @Override
            public boolean isSingleTon() {
                return false;
            }
        }
    ```

    ```xml
        <beans>
            <bean id="message" class="springbook.factorybean.MessageFactoryBean">
                <property name="text" value="Factory Bean Test"/>
            </bean>
        </beans>
    ```

    ```java
        @RunWith(SpringJUnit4ClassRunner.class)
        @ContextConfiguration(locations="/FactoryBeanTest-context.xml")
        public class FactoryBeanTest {
            @Autowired
            private ApplicationContext context;

            
            @Test 
            public void factoryBeanTest() {
                Object message = context.getBean("message");
                assertThat(message, is(Message.class));
                assertThat(message.getText(), is("Factory Bean Test"));    
            }


            @Test
            public void getFactoryBean() throws Exception {
                Object factory = context.getBean("&message");
                assertThat(factory, is(MessageFactoryBean.class));
            }
        }
    ```


---


## 🐫 프록시 팩토리 빈

* **프록시** 객체생성을 추상화한 팩토리빈 이다.

* 팩토리빈 객체를 생성할 떄 필요했던 **타겟** 은, 프록시 팩토리 빈에는 필요 없다.

* 타겟정보는 프록시 팩토리 빈 생성 후, **setTarget()** 메서드로 설정한다.

* 필요 라이브러리

    * com.springsource.org.aopalliance-1.0.0.jar
    
    * org.springframework.aop-3.0.7.RELEASE.jar

---


## 🐫 Advice : (타겟정보가 필요없는) 순수 부가기능

* **addAdvice()**의 인자는 **Advice 인터페이스**를 구현한 객체이다.

* **Advice 인터페이스**는 타겟에 **종속되지 않는**, **순수한 부가기능 오브젝트** 이다.

* 부가기능을 담당할 클래스는 **MethodInterceptor 인터페이스** 를 구현한다. (MethodInterceptor는 Advice의 하위 인터페이스)

* 사용법

    ```java
        public class UppercaseAdvice implements MethodInterceptor {
            @Override
            public Object invoke(MethodInvocation invocation) throws Exception {
                String result = (String)invocation.proceed();
                return result.toUpperCase();
            }
        }
    ```

    ```java
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());
        pfBean.addAdvice(new UppercaseAdvice());

        Hello proxiedHello = (Hello)pfBean.getObject();
        assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
        assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
        assertThat(proxiedHello.sayThankYou("Toby"), is("THANK YOU TOBY"));
    ```

---

> ### 🐫 Pointcut

* Advice를 적용할 대상 메서드를 선별하는 역할을 한다.

* 메서드 이름으로 선정하는 pointcut 클래스는 **NameMatchMethodPointcut** 이다.

* 메서드 이름 조건을 설정할 때는, NameMatchMethodPointcut객체의 **setMappedName("조건")** 으로 설정한다.

    * 조건값에 *를 사용할 경우, **LIKE** 조건이 된다.

* ProxyFactoryBean에 Pointcut을 설정할 때는, Pointcut객체와 Advice객체를 묶은 **Advisor**객체로 등록해야 한다.

* ProxyFactoryBean에 Advisor를 등록할 때는, **addAdvisor(pointcut객체, advice객체);** 형식으로 등록한다.

* 사용법

    ```java
        // Advice만 사용할 경우,
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(대상 객체);
        pfBean.addAdvice(Advice객체);
    ```

    ```java
        // 메서드명을 이용한 Pointcut을 사용할 경우,
        NameMatchMethodPointcut pointcut = new NameMatchMethodPointcut();
        pointcut.setMappedName("sayH*");

        MethodInterceptor advice = new UppercaseAdvice();
        
        ProxyFactoryBean pfBean = new ProxyFactoryBean();
        pfBean.setTarget(new HelloTarget());
        pfBean.addAdvisor(new DefaultPointcutAdvisor(pointcut, advice));

        Hello proxiedHello = (Hello)pfBean.getObject();
        assertThat(proxiedHello.sayHello("Toby"), is("HELLO TOBY"));
        assertThat(proxiedHello.sayHi("Toby"), is("HI TOBY"));
        assertThat(proxiedHello.sayThankYou("Toby"), is("Thank you Toby"));
    ```

* xml을 이용한 ProxyFactoryBean DI설정하기

    ```xml
        <bean id="userServiceImpl" class="경로">
            <property name="userDao" ref="userDao"/>
        </bean>

        <bean id="transactionAdvice" class="TransactionAdvice경로">
            <property name="transactionManager" ref="transactionManager"/>
        </bean>

        <bean id="transactionPointcut" class="org.springframework.aop.support.NameMatchMethodPointcut">
            <property name="mappedName" value="sayH*"/>
        </bean>

        <bean id="transactionAdvisor" class="org.springframework.aop.support.DefaultPointcutAdvisor">
            <property name="advice" ref="transactionAdvice"/>
            <property name="pointcut" ref="transactionPointcut"/>
        </bean>

        <bean id="userService" class="org.springframework.aop.framework.ProxyFactoryBean">
            <property name="target" ref="userServiceImpl"/>
            <property name="interceptorNames">
                <list>
                    <value>transactionAdvisor</value>
                </list>
            </property>
        </bean>
    ```


---


## 🐫 자동 프록시 생성기 : DefaultAdvisorAutoProxyCreator

* **빈 후처리기** 를 이용하여 설정된 Advisor를 적용한 Proxy객체를 반환해 준다.

* 주의할 점은, 반환해 주는 객체의 타입이 **타겟(Target)**의 **구현 인터페이스**라는 것이다.

* xml설정은 다음과 같다.

    ```xml
        <bean id="transactionAdvice" class="MethodInterceptor를 구현한 클래스경로"></bean>

        <bean id="transactionPointcut" class="NameMatchMethodPointcut을 상속한 클래스경로">
            <property name="mappedName" value="메서드 패턴"/>
            <!-- NameMatchMethodPointcut의 ClassFilter를 교체하여 클래스명 패턴도 정할 수 있다 -->
        </bean>

        <bean id="transactionAdvisor" class="org.springframework.aop.support.DefaultPointcutAdvisor">
            <property name="advice" ref="transactionAdvice"/>
            <property name="pointcut", ref="transactionPointcut"/>
        </bean>

        <!-- 프록시를 자동생성하여,  -->
        <bean class="org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator"/>
    ```

* NameMatchMethodPointcut 클래스의 속성으로 ClassFilter객체가 클래스명을 필터링해준다.

* ClassFilter는 기본적으로 모든 클래스가 true로 필터링이 off인 상태이다.

* NameMatchMethodPointcut 클래스의 ClassFilter를 다음과 같이 바꿔주면, 클래스명을 필터링 할 수 있다.

    ```java
        // 클래스명, 메서드명 둘 다 필터링 가능한 Pointcut클래스 작성
        public class NameMatchClassMethodPointcut extends NameMatchMethodPointcut {
            public void setMappedClassName(String mappedClassName) {
                this.setClassFilter(new SimpleClassFilter(mappedClassName));
            }

            // 클래스명 필터기능을 설정한 ClassFilter타입 클래스
            static class SimpleClassFilter implements ClassFilter {
                // 클래스명을 필터링할 네임패턴
                private String mappedName;

                public SimpleClassFilter(String mappedName) {
                    this.mappedName = mappedName;
                }

                // 실제로 패턴비교를 수행하는 메서드를 @Override
                @Override
                public boolean matches(Class<?> clazz) {
                    return PatternMatchUtils.simpleMatch(mappedName, clazz.getSimpleName());
                }

            }
        }
    ```


---


## 🐫 스프링 참고지식

* PatternMatchUtils.simpleMatch(String name_1, String name_2)

    * 와일드카드(*)를 지원하는 스프링의 비교 유틸 메서드다.

* <bean parent="부모객체id">

    * 부모객체 빈(Bean)의 모든 속성값을 그대로 가져온다. (상속받았기 때문에 가능하다.)