# Cucumber-with-guice

<p> This is a API test framework repo with an example of simple post API call. </p>

### Project details
- Java (JDK 17)
- DI using Guice
- RestAssured
- IntellliJ

### How to run the cucumber test?
#### command prompt:
```java
mvn clean install -Denv=qa
```
### IntellliJ 
- Run java class: ApiTestRunner [a link] (https://github.com/SunilGhargaonkar/cucumber-with-guice/blob/master/src/test/java/org/example/cucumber/with/guice/runners/ApiTestRunner.java)
- Run a cucumber file: UserCreation.feature [a link] (https://github.com/SunilGhargaonkar/cucumber-with-guice/blob/master/src/test/resources/features/UserCreation.feature)