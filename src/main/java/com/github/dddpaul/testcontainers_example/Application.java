package com.github.dddpaul.testcontainers_example;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@SpringBootApplication
@RestController
public class Application {

    @Bean
    MongoClient mongoClient() {
        return new MongoClient("localhost");
    }

    @RequestMapping(value = "/", method = GET, produces = "application/json")
    MongoIterable<Document> index() {
        MongoCollection<Document> users = mongoClient().getDatabase("test").getCollection("users");
        users.updateOne(eq("_id", 1), set("name", "paul"), new UpdateOptions().upsert(true));
        return users.find();
    }

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }
}
