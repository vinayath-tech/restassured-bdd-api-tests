# restassured-bdd-api-tests
To demonstrate creation of API test framework using restassured, docker, java

## Introduction
Sample api test framework created using the following tools & libraries:
```
- Cucumber-jvm
- RestAssured
- java8
- testNG
- Docker
- Maven
```

## Run tests using Docker
To build the test container, execute the below command:
```
docker run build -t=gokul/rest-api-test .
```
To run the tests inside the container, execute the below command:
```
docker-compose up —abort-on-container-exit —exit-code-from rest-api-tests
```
--abort-on-container-exit - simply tears down the stack once the test container finishes.
-exit-code-from - Return the exit code of the selected service container. Implies --abort-on-container-exit.

## Run tests locally on a machine
To execute the tets locally on a machine, execute the below command:
```
mvn test -P test
```
