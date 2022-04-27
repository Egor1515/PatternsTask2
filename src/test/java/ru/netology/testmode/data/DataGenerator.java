package ru.netology.testmode.data;

import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.Value;

<<<<<<< HEAD

import java.util.Locale;

import static com.codeborne.selenide.Selenide.$;
import static io.restassured.RestAssured.given;
=======
import java.util.Locale;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;
>>>>>>> 4d61a42d42d57956668eae3016ab195a662c601b

public class DataGenerator {
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static final Faker faker = new Faker(new Locale("en"));

    private DataGenerator() {
    }

    private static void sendRequest(RegistrationDto user) {
        given()
                .spec(requestSpec)
<<<<<<< HEAD
                .body(new RegistrationDto("vasya", "password", "active"))
                .when() // "когда"
                .post("/api/system/users")
                .then() // "тогда ожидаем"
                .statusCode(200);
    }

=======
                .body(new RegistrationDto("vasya", "123123", "active"));
        when()
                .post("api/system/users")
                .then()
                .statusCode(200);

    }
>>>>>>> 4d61a42d42d57956668eae3016ab195a662c601b

    public static String getRandomLogin() {
        var login = faker.funnyName().name();
        return login;
    }

    public static String getRandomPassword() {
        var password = faker.numerify("#####");
        return password;
    }

    public static class Registration {
        private Registration() {
        }

        public static RegistrationDto getUser(String status) {
            return new RegistrationDto(getRandomLogin(), getRandomPassword(), "active");

        }

        public static RegistrationDto getRegisteredUser(String status) {
            var registeredUser = getUser("active");
            sendRequest(registeredUser);
            return registeredUser;
        }
        public static RegistrationDto notRegisteredUser (String status) {
            var notRegisteredUser = getUser("active");
<<<<<<< HEAD
            return notRegisteredUser;
        }
        public static RegistrationDto blockedUser (String status) {
            var notRegisteredUser = getUser("blocked");
            return notRegisteredUser;
=======
return notRegisteredUser;
        }
        public static RegistrationDto blockedUser (String status) {
            var notRegisteredUser = getUser("blocked");
return notRegisteredUser;
>>>>>>> 4d61a42d42d57956668eae3016ab195a662c601b
        }
    }

    @Value
    public static class RegistrationDto {
        String login;
        String password;
        String status;
    }
<<<<<<< HEAD
}
=======
}
>>>>>>> 4d61a42d42d57956668eae3016ab195a662c601b
