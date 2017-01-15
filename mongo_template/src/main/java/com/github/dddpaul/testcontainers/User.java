package com.github.dddpaul.testcontainers;

import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class User {

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
