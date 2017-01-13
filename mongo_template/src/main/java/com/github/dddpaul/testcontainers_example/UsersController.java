package com.github.dddpaul.testcontainers_example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.FindAndModifyOptions;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.PostConstruct;
import java.util.List;

import static org.springframework.data.mongodb.core.query.Criteria.where;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private MongoTemplate mongoTemplate;

    @PostConstruct
    private void init() {
        mongoTemplate.findAndModify(
                new Query(where("_id").is(1)),
                new Update().set("name", "paul"),
                new FindAndModifyOptions().upsert(true).returnNew(true),
                User.class);
    }

    @RequestMapping(value = "/", method = GET, produces = "application/json")
    public List<User> index() {
        List<User> all = mongoTemplate.findAll(User.class);
        return all;
    }
}
