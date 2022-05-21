package ru.netology;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Condition.ownText;
import static com.codeborne.selenide.Selectors.byName;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class AuthTest {
    
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }
    
    @Test
    void shouldLoginWithRegisteredActiveUser() {
        RegistrationDto registeredUser = DataGenerator.getRegisteredUser("active");
        $(byName("login")).setValue(registeredUser.getLogin());
        $(byName("password")).setValue(registeredUser.getPassword());
        $("[data-test-id=action-login]").click();
        
        $("[id=root] h2").should(ownText("Личный кабинет"));
    }
    
    @Test
    void shouldGetErrorWithNotRegisteredUser() {
        RegistrationDto notRegisteredUser = DataGenerator.getUser("active");
        $(byName("login")).setValue(notRegisteredUser.getLogin());
        $(byName("password")).setValue(notRegisteredUser.getPassword());
        $("[data-test-id=action-login]").click();
        
        $(".notification__title").should(ownText("Ошибка"), Duration.ofSeconds(5));
        $(".notification__content").should(ownText("Ошибка! \r\n" + "Неверно указан логин или пароль"),
                Duration.ofSeconds(5));
    }
    
    @Test
    void shouldGetErrorWithWrongLogin() {
        RegistrationDto registeredUser = DataGenerator.getRegisteredUser("active");
        String wrongLogin = DataGenerator.getRandomLogin();
        $(byName("login")).setValue(wrongLogin);
        $(byName("password")).setValue(registeredUser.getPassword());
        $("[data-test-id=action-login]").click();
        
        $(".notification__title").should(ownText("Ошибка"), Duration.ofSeconds(5));
        $(".notification__content").should(ownText("Ошибка! \r\n" + "Неверно указан логин или пароль"),
                Duration.ofSeconds(5));
    }
    
    @Test
    void shouldGetErrorWithWrongPassword() {
        RegistrationDto registeredUser = DataGenerator.getRegisteredUser("active");
        String wrongPassword = DataGenerator.getRandomPassword();
        $(byName("login")).setValue(registeredUser.getLogin());
        $(byName("password")).setValue(wrongPassword);
        $("[data-test-id=action-login]").click();
        
        $(".notification__title").should(ownText("Ошибка"), Duration.ofSeconds(5));
        $(".notification__content").should(ownText("Ошибка! \r\n" + "Неверно указан логин или пароль"),
                Duration.ofSeconds(5));
    }
    
    @Test
    void shouldGetErrorWithBlockedUser() {
        RegistrationDto blockedUser = DataGenerator.getRegisteredUser("blocked");
        $(byName("login")).setValue(blockedUser.getLogin());
        $(byName("password")).setValue(blockedUser.getPassword());
        $("[data-test-id=action-login]").click();
        
        $(".notification__title").should(ownText("Ошибка"), Duration.ofSeconds(5));
        $(".notification__content").should(ownText("Ошибка! \r\n" + "Пользователь заблокирован"),
                Duration.ofSeconds(5));
    }
}
