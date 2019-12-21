FROM    maven:3.6.3-jdk-13

WORKDIR /usr/share/api-test-proj

ADD     src/              src/
ADD     pom.xml           pom.xml
ADD     extent-config.xml extent-config.xml
ENTRYPOINT mvn test -P $PROFILE
