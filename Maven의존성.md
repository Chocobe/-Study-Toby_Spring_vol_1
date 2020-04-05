# 🐫 Maven 의존성 정리

* Eclipse에서 ``Spring Legacy project``생성 시, 추가해야 할 의존성 정리

* 버전 충돌 확인 완료

---

## 🐫 JUnit 기본설정

    ```xml
        <dependency>
		    <groupId>junit</groupId>
		    <artifactId>junit</artifactId>
		    <version>4.8.1</version>
		    <scope>test</scope>
		</dependency>
		
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>
    ```

* **spring-test** 의 버전은 ``spring-context``의 버전과 일치해야만 한다. (spring-context의 버전 : ${org.springframework-version})

---

## 🐫 Jackson - AJAX를 통한 JSON 컨버터

    ```xml
        <dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-mapper-asl</artifactId>
			<version>1.9.13</version>
		</dependency>
    ```

---

## 🐫 commons-beanutils - JSP에서 Servlet으로 전송한 데이터를 Java객체로 바인딩 해준다.

    ```xml
        <dependency>
			<groupId>commons-beanutils</groupId>
			<artifactId>commons-beanutils</artifactId>
			<version>1.8.0</version>
		</dependency>
    ```

---

## 🐫 MySQL

    ```xml
		<dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.48</version>
		</dependency>
    ```

---

## 🐫 commons-dbcp - 커넥션풀을 사용할 수 있다.

    ```xml
		<dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.2.2</version>
		</dependency>
    ```

---

## 🐫 cglib(Code Generator library) - 런타임 시점에 동적으로 Java class를 Proxy로 만들어 준다.

    ```xml
        <dependency>
			<groupId>cglib</groupId>
			<artifactId>cglib-nodep</artifactId>
			<version>2.2</version>
		</dependency>
    ```

* **<aop:advisor>**를 사용할 때, ``pointcut="execution(* *.*.*(..))"`` 프로퍼티를 **Proxy** 클래스로 만들어 빈(Bean)으로 등록해 준다.

---

## 🐫 MyBatis

    ```xml
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.1.0</version>
		</dependency>
		
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.1.0</version>
		</dependency>
    ```

* **mybatis-spring** 은 Spring에서 MyBatis를 사용하기 위한 라이브러리다.

---

# 🐫 전체 <dependency>

    ```xml
        <!-- JUnit생략(필요시 추가할 것) -->

        <!-- spring-test -->
        <dependency>
            <groupId>org.springframework</groupId>
            <artifactId>spring-test</artifactId>
            <version>${org.springframework-version}</version>
        </dependency>

        <!-- jackson -->
        <dependency>
			<groupId>org.codehaus.jackson</groupId>
			<artifactId>jackson-mapper-asl</artifactId>
			<version>1.9.13</version>
		</dependency>

        <!-- commons-beanutils -->
        <dependency>
			<groupId>commons-beanutils</groupId>
			<artifactId>commons-beanutils</artifactId>
			<version>1.8.0</version>
		</dependency>

        <!-- MySQL -->
        <dependency>
			<groupId>mysql</groupId>
			<artifactId>mysql-connector-java</artifactId>
			<version>5.1.48</version>
		</dependency>

        <!-- commons-dbcp -->
        <dependency>
			<groupId>commons-dbcp</groupId>
			<artifactId>commons-dbcp</artifactId>
			<version>1.2.2</version>
		</dependency>

        <!-- cglib -->
        <dependency>
			<groupId>cglib</groupId>
			<artifactId>cglib-nodep</artifactId>
			<version>2.2</version>
		</dependency>

        <!-- mybatis -->
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis</artifactId>
			<version>3.1.0</version>
		</dependency>
		
		<dependency>
			<groupId>org.mybatis</groupId>
			<artifactId>mybatis-spring</artifactId>
			<version>1.1.0</version>
		</dependency>
    ```