package com.kahesama.demo.curso_spring_s12_api;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource(locations = "classpath:application-test.properties")
class CursoSpringS12WebApiRestfulApplicationTests {

	@Test
	void contextLoads() {
	}

}
