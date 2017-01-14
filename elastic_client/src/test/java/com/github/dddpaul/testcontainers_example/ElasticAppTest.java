package com.github.dddpaul.testcontainers_example;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = RANDOM_PORT, classes = {ElasticApp.class, TestConfiguration.class})
public class ElasticAppTest extends TestConfiguration {

    @Autowired
    TestRestTemplate restTemplate;

    @Before
    public void setUp() throws Exception {
        elasticsearchClient()
                .prepareIndex("users", "user", "1")
                .setSource("{\"name\": \"paul\"}")
                .execute()
                .actionGet();
    }

    @Test
    public void shouldReturnUserList() {
        List users = restTemplate.getForObject("/users/", List.class);
        assertFalse(users.isEmpty());
    }
}
