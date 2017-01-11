FROM dddpaul/jdk-standard

RUN mkdir /var/tests
ADD build/distributions/testcontainers_example.tar /var/tests

# Force Gradle to download all of its dependencies at image build time!
RUN rm -rf /root/.gradle
RUN cd /var/tests && ./gradlew --refresh-dependencies resolveDependencies

EXPOSE 8080
WORKDIR /var/tests
ENTRYPOINT ["./gradlew", "--offline", "clean", "test"]
