package com.github.dddpaul.testcontainers_example;

import com.mongodb.MongoClient;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoIterable;
import com.mongodb.client.model.UpdateOptions;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;

import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Updates.set;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private MongoClient mongoClient;

    private MongoCollection<Document> users;

    @PostConstruct
    private void init() {
        users = mongoClient.getDatabase("test").getCollection("users");
        users.updateOne(eq("_id", 1), set("name", "paul"), new UpdateOptions().upsert(true));
    }

    @RequestMapping(value = "/", method = GET, produces = "application/json")
    public MongoIterable<Document> index() {
        return users.find();
    }
}
