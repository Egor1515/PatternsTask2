import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Duration;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static ru.netology.testmode.data.DataGenerator.Registration.getRegisteredUser;
import static ru.netology.testmode.data.DataGenerator.Registration.getUser;
import static ru.netology.testmode.data.DataGenerator.getRandomLogin;
import static ru.netology.testmode.data.DataGenerator.getRandomPassword;

class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var registeredUser = getRegisteredUser("active");
        $("[data-test-id='login'] input").setValue("vasya");
        $("[data-test-id='password'] input").setValue("password");
        $(".button[role='button']").click();
        $("App_appContainer__3jRx1").should(Condition.visible, Duration.ofSeconds(15));

    }

    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {
        var registeredUser = getRegisteredUser("active");
        var notRegisteredUser = getUser("active");
        $("[data-test-id='login'] input").setValue("oleg");
        $("[data-test-id='password'] input").setValue("p124d");
        $(".button[role='button']").click();
        $(".notification__title").should(Condition.text("Ошибка"));
        $(".notification__content").should(Condition.text("Неверно указан логин или пароль"));


    }

    @Test
    @DisplayName("Should get error message if login with blocked registered user")
    void shouldGetErrorIfBlockedUser() {
        var blockedUser = getRegisteredUser("blocked");
        $("[data-test-id='login'] input").setValue("vasya");
        $("[data-test-id='password'] input").setValue("password");
        $(".button[role='button']").click();
        $(".notification__title").should(Condition.text("Ошибка"));
        $(".notification__content").should(Condition.text("Неверно указан логин или пароль"));


    }

    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
        var registeredUser = getRegisteredUser("active");
        var wrongLogin = getRandomLogin();
        $("[data-test-id='login'] input").setValue(wrongLogin);
        $("[data-test-id='password'] input").setValue("password");
        $(".button[role='button']").click();
        $(".notification__title").should(Condition.text("Ошибка"));
        $(".notification__content").should(Condition.text("Неверно указан логин или пароль"));


    }

    @Test
    @DisplayName("Should get error message if login with wrong password")
    void shouldGetErrorIfWrongPassword() {
        var registeredUser = getRegisteredUser("active");
        var wrongPassword = getRandomPassword();
        $("[data-test-id='login'] input").setValue("vasya");
        $("[data-test-id='password'] input").setValue(wrongPassword);
        $(".button[role='button']").click();
        $(".notification__title").should(Condition.text("Ошибка"));
        $(".notification__content").should(Condition.text("Неверно указан логин или пароль"));


    }
}