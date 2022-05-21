package ru.netology;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import java.util.Locale;

import static io.restassured.RestAssured.given;

public class DataGenerator {
    private static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    
    private static Faker faker = new Faker(new Locale("en"));
    
    private DataGenerator() {
    }
    
    private static void sendRequest(RegistrationDto user) {
        given()
                .spec(requestSpec) // указываем, какую спецификацию используем
                .body(user) // передаём в теле объект, который будет преобразован в JSON
                .when()
                .post("/api/system/users") // на какой путь, относительно BaseUri отправляем запрос
                .then()
                .statusCode(200);
    }
    
    public static String getRandomLogin() {
        String login = faker.name().lastName();
        return login;
    }
    
    public static String getRandomPassword() {
        String password = faker.bothify("???##");
        return password;
    }
    
    public static RegistrationDto getUser(String status) {
        RegistrationDto user = new RegistrationDto(getRandomLogin(), getRandomPassword(), status);
        return user;
    }
    
    public static RegistrationDto getRegisteredUser(String status) {
        RegistrationDto registeredUser = getUser(status);
        sendRequest(registeredUser);
        return registeredUser;
    }
}
