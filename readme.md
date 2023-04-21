# 스프링 부트와 AWS로 혼자 구현하는 웹 서비스

--- 
- 생성 : SpringInitialzr를 이용해서 생성
- 버전 : java 17, gradle, war 세팅
- 의존성 : SpringBootDevTools, Lombok, SpringConfigurationProcessor 선택
---

---

---
## 책 코드 스프링부트3에서 작동안되는 부분 체크

- 62페이지<br/>

아래 코드 중 하나를 클래스 위에 붙여야 사용 가능<br/>
@SpringBootTest
<br/>
@AutoConfigureMockMvc

@ExtendWith(SpringExtension.class)
<br/>
@WebMvcTest(controller<br/><br/>
mvc.perform(MockMvcRequestBuilders.get("/hello"))
<br/>
.andExpect(MockMvcResultMatchers.status().isOk())
<br/>
.andExpect(MockMvcResultMatchers.content().string(hello));
<br/><br/>

Junit5버전으로 오며 get(), statuc(), content() 메서드는 앞에 클래스명을 붙여야 호출됨.

---

- 73페이지<br>

Assertions.assertThat(dto.getName()).isEqualTo(name);
<br>
Assertions.assertThat(dto.getAmount()).isEqualTo(amount);
<br><br>
역시 마찬가지고 Assertions 라는 클래스명을 붙여야 assertThat()) 사용 가능

---

- 76페이지<br>

jsonPath() -> MockMvcResultMatchers.jsonPath()
<br>
is() -> Matchers.is()

---

- 86페이지<br>
// https://mvnrepository.com/artifact/org.springframework.boot/spring-boot-starter-data-jpa
implementation group: 'org.springframework.boot', name: 'spring-boot-starter-data-jpa', version: '3.0.5'
<br>
// https://mvnrepository.com/artifact/com.h2database/h2
testImplementation group: 'com.h2database', name: 'h2', version: '2.1.214'
<br><br>
의존성은 위와 같이 추가했음. 버전은 달라질 수 있음

---

- 96페이지

@After -> @AfterEach
<br>
@Before -> @BeforeEach
<br><br>

---

- 100페이지

spring.jpa.show-sql=true
<br>
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL57Dialect
<br>
spring.jpa.properties.hibernate.dialect.storage_engine=innodb
<br>
spring.datasource.hikari.jdbc-url=jdbc:h2:mem://localhost/~/testdb;MODE=MYSQL
<br>
spring.h2.console.enabled=true
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
