tescontainers-examples
=========

[TestContainers](https://www.testcontainers.org/) is a Java library that supports JUnit tests, providing lightweight, throwaway instances of common databases, Selenium web browsers, or anything else that can run in a Docker container.

TestContainers package contains several [GenericContainer](https://github.com/testcontainers/testcontainers-java/blob/master/core/src/main/java/org/testcontainers/containers/GenericContainer.java) implementations JDBC-like mostly. This project demonstrates how to mock up NoSQL database dependencies:

* [elastic_client](elastic_client) - [org.elasticsearch.client.Client](https://www.elastic.co/guide/en/elasticsearch/client/java-api/current/client.html) is mocked with [elasticsearch:5.1.2](https://hub.docker.com/_/elasticsearch/) container;
* [mongo_client](mongo_client) - [MongoClient](http://api.mongodb.com/java/current/com/mongodb/MongoClient.html) is mocked with [mvertes/alpine-mongo:3.2.10-3](https://hub.docker.com/r/mvertes/alpine-mongo/) container;
* [mongo_template](mongo_template) - [MongoTemplate](http://docs.spring.io/spring-data/mongodb/docs/current/api/org/springframework/data/mongodb/core/MongoTemplate.html) is mocked with [mvertes/alpine-mongo:3.2.10-3](https://hub.docker.com/r/mvertes/alpine-mongo/) container.

The most useful class is a [GenericContainer](https://github.com/testcontainers/testcontainers-java/blob/master/core/src/main/java/org/testcontainers/containers/GenericContainer.java) implementation which allows to run Docker container from another Docker container with docker.sock mounted from host.

The real life example is running tests from Jenkins build container. To simulate Jenkins container tests are run from Docker container run by Gradle.
