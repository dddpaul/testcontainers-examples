package com.github.dddpaul.testcontainers_example;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.search.SearchHit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private Client elasticsearchClient;

    @RequestMapping(value = "/", method = GET, produces = "application/json")
    public List<Map<String, Object>> index() {
        SearchResponse response = elasticsearchClient
                .prepareSearch("users")
                .execute()
                .actionGet();
        return Stream.of(response.getHits().getHits())
                .map(SearchHit::getSource)
                .collect(toList());
    }
}
