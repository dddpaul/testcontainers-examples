package com.github.dddpaul.testcontainers_example;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ApplicationTest {

	@Autowired
	TestRestTemplate restTemplate;

	@Test
	public void shouldReturnUserList() {
		List users = restTemplate.getForObject("/", List.class);
		assertFalse(users.isEmpty());
	}
}
