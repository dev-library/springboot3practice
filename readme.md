# 스프링 부트와 AWS로 혼자 구현하는 웹 서비스

--- 
- 생성 : SpringInitialzr를 이용해서 생성
- 버전 : java 17, gradle, war 세팅
- 최초의존성 : SpringBootDevTools, Lombok, SpringConfigurationProcessor 선택
---

---

---
## 책 코드 스프링부트3에서 작동안되는 부분 체크

- 62페이지<br/>

아래 어노테이션 조합쌍 중 하나를 클래스 위에 붙여야 사용 가능<br/>
`@SpringBootTest`
`@AutoConfigureMockMvc`

`@ExtendWith(SpringExtension.class)`
`@WebMvcTest(controller)`
<br/><br/>
Junit5버전으로 오며 get(), status(), content() 메서드는 앞에 클래스명을 붙여야 호출됨.
`mvc.perform(MockMvcRequestBuilders.get("/hello"))
.andExpect(MockMvcResultMatchers.status().isOk())
.andExpect(MockMvcResultMatchers.content().string(hello));`




---

- 73페이지<br>

`Assertions.assertThat(dto.getName()).isEqualTo(name);`

`Assertions.assertThat(dto.getAmount()).isEqualTo(amount);`
<br><br>
역시 마찬가지고 Assertions 라는 클래스명을 붙여야 assertThat()) 사용 가능

---

- 76페이지<br>

`jsonPath()` -> `MockMvcResultMatchers.jsonPath()`

`is()` -> `Matchers.is()`

---

- 86페이지<br>

`// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
implementation group: 'org.springframework.boot', name: 'spring-boot-starter-data-jpa', version: '3.0.5'`

`// https://mvnrepository.com/artifact/com.h2database/h2
testImplementation group: 'com.h2database', name: 'h2', version: '2.1.214'`
<br><br>
의존성은 위와 같이 추가했음. 버전은 달라질 수 있음

---

- 96페이지

`@After -> @AfterEach`
`@Before -> @BeforeEach`
<br><br>

---

- 100페이지

`spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
spring.jpa.properties.hibernate.dialect.storage_engine=innodb
spring.datasource.hikari.jdbc-url=jdbc:h2:mem://localhost/~/testdb;MODE=MYSQL
spring.h2.console.enabled=true`
<br><br>
application.properties에 추가해야 하는 문장을 책에 나온거 말고 위에꺼로 해야함

---

- 107페이지

무슨 이슈인지는 모르지만 책에 써진대로 작성하니 처음에 save()가 재귀호출함<br>
예상하는 이슈는 PostsSaveRequestDTO가 작성 안된 상태로 다른게 먼저 작성되어서?<br>
작성 클래스 순서를 바꾸거나 재인식 시켜야 하는데 아래 항목 체크해야함<br><br>
호출되는 메서드가 뭔지 패키지 클릭해서 확인해보기<br>
postsRepository가 보라색이어야 인식된거임. 회색이면 다시 체크해보기.<br>
<br><br>
뭔가 인식이 잘 안 된다 싶으면 클래스명부터 다시 적고 작성해보기.

---

- 116페이지

1. build.gradle의 dependencies에 `implementation "com.h2database:h2"`를추가
2. h2콘솔에 접속한 다음 <br>
`spring.datasource.hikari.jdbc-url=jdbc:h2:mem://localhost/~/testdb;MODE=MYSQL`

위와 같이 작성했었으니, JDBC url에도 `jdbc:h2:mem://localhost/~/testdb;MODE=MYSQL` 
<br>
입력 후 connect버튼 클릭하기.
3. `#spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
   #spring.jpa.properties.hibernate.dialect.storage_engine=innodb`
<br>
추가로 첨언하자면 100페이지에서 추가했던 것들 중 위 둘은 주석처리 해도 됨

---

- 130페이지

1. 인텔리제이를 실행합니다.
2. 상단 메뉴에서 File > Settings를 선택합니다.
3. 왼쪽 메뉴에서 Plugins를 선택합니다.
4. 오른쪽 상단에 있는 Marketplace 버튼을 클릭합니다.
5. 검색창에 Mustache를 입력하고, Search in repositories를 선택합니다.
6. 검색 결과에서 Handlebars/Mustache 플러그인을 선택하고, Install 버튼을 클릭합니다.
7. 설치가 완료되면, Restart IDE 버튼을 클릭해서 인텔리제이를 재시작합니다.
8. Restart IDE 버튼은 플러그인을 설치한 후 나타납니다.
9. 만약 플러그인을 설치한 후 Restart IDE 버튼이 나타나지 않는다면, File > Invalidate Caches / Restart를 선택하면 됩니다. 이렇게 하면 인텔리제이가 종료되고, 재시작됩니다.

---

- 134페이지

1. 스프링부트 3버전으로 테스트시 한글이 깨져서 나온다면 application.properties에
<br>
`server.servlet.encoding.force-response=true` 을 추가하면 한글인코딩이 안깨집니다.

---

- 143페이지 index.js 추가 부분


1. src 경로 지정할때 자동완성 안 됨. 그냥 교재 나온대로 쳐야함.


---

- 148페이지

1. `@Transactional` 어노테이션 쓸 때 org.springframework.transaction.annotation 을 import 할것

---

- 150페이지

1. 혹시라도 IndexController 클래스 위에 `@RequiredArgsConstructor` 빼먹었다면 다시 체크하기.

---

- 170페이지

1. OAuth클라이언트 아이디를 만드려면 먼저 OAuth 동의화면을 구성시켜야 합니다.
2. 구성되고 나서 사이드바의 사용자 인증 정보 -> OAuth 클라이언트 ID 만들기 선택!

---

- 180 페이지

1. 그간 `WebSecurityConfigurerAdapter` 클래스를 상속해서 `SecurityConfig`클래스를 작성했으나
   deprecated 되어 사용할 수 없는 상황입니다.
2. 따라서 `SecurityFilterChain`을 `@Bean`으로 등록해 써야합니다.
3. https://spring.io/blog/2022/02/21/spring-security-without-the-websecurityconfigureradapter
4. 요약하자면, 현재 쓸 수 deprecated된 것들을 따져보자면 
<br>
`antMatchers()` -> `requestMatchers()`, `authorizeRequests()` -> `authorizeHttpRequests()`
5. 그리고 파라미터로 받은 `http` 객체는 마지막에 `http.build()` 구문으로 빌드해 리턴합니다.
 

---

- 191 페이지

1. `User`는 예약어이므로 이 책에서 `User`라는 Entity는 변경해야함.
2. `Member`로 변경하고 다시 해봄.
3. 해결됨. 안 되는 책이 출시되었을리는 없으니 뭔가 바뀐게 분명함. 
4. 한 가지 의문점은 시큐리티 적용 전에는 그럼 왜 User 명칭 테이블 생성이 됨?
5. 갑작스러운 깨달음은 시큐리티에서 User를 예약어로 쓰는듯.
6. 근데 그럼 이 책에서는 왜 이 부분을 언급 안하고 가지? 다시 이해 안감.

---

