import com.codeborne.selenide.Condition;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;


class AuthTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @Test
    @DisplayName("Should successfully login with active registered user")
    void shouldSuccessfulLoginIfRegisteredActiveUser() {
        var registeredUser = DataGenerator.Registration.getRegisteredUser("active");
        $("[data-test-id='login'] input").setValue("vasya");
        $("[data-test-id='password'] input").setValue("password");
        $(".button[role='button']").click();
        $("[id='root'] div").should(Condition.visible);

    }

    @Test
    @DisplayName("Should get error message if login with not registered user")
    void shouldGetErrorIfNotRegisteredUser() {

        var notRegisteredUser = DataGenerator.Registration.getUser("active");
        $("[data-test-id='login'] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id='password'] input").setValue(DataGenerator.getRandomPassword());
        $(".button[role='button']").click();
        $("[data-test-id='error-notification']").should(Condition.visible);

    }

    @Test
    @DisplayName("Should get error message if login with blocked registered user")
    void shouldGetErrorIfBlockedUser() {
        var blockedUser = DataGenerator.Registration.getUser("blocked");
        $("[data-test-id='login'] input").setValue(DataGenerator.getRandomLogin());
        $("[data-test-id='password'] input").setValue(DataGenerator.getRandomPassword());
        $(".button[role='button']").click();
        $("[data-test-id='error-notification']").should(Condition.visible);


    }

    @Test
    @DisplayName("Should get error message if login with wrong login")
    void shouldGetErrorIfWrongLogin() {
        var registeredUser = DataGenerator.Registration.getUser("active");
        var wrongLogin = DataGenerator.getRandomLogin();
        $("[data-test-id='login'] input").setValue(wrongLogin);
        $("[data-test-id='password'] input").setValue(DataGenerator.Registration.getRegisteredUser("active").getLogin());
        $(".button[role='button']").click();
        $("[data-test-id='error-notification']").should(Condition.visible);

    }

    @Test
    @DisplayName("Should get error message if login with wrong password")
    void shouldGetErrorIfWrongPassword() {
        var registeredUser = DataGenerator.Registration.getUser("active");
        var wrongPassword = DataGenerator.getRandomPassword();
        $("[data-test-id='login'] input").setValue("vasya");
        $("[data-test-id='password'] input").setValue(wrongPassword);
        $(".button[role='button']").click();
        $("[data-test-id='error-notification']").should(Condition.visible);
    }
}