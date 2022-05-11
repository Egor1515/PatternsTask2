import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Value;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class DataGenerator {

    @Value
    public static class RegistrationDto {
        String login;
        String password;
        String status;
    }


    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();
    private static final Faker faker = new Faker(new Locale("ru"));

    private DataGenerator() {
    }

    public static void sendRequest(RegistrationDto user) {
        given()
                .spec(requestSpec)
                .body(user)
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

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
            return new DataGenerator.RegistrationDto(getRandomLogin(), getRandomPassword(), "active");


        }

        public static RegistrationDto getRegisteredUser(String status) {
            var registeredUser = getUser("active");
            sendRequest(registeredUser);
            return registeredUser;
        }

        public static RegistrationDto getNotRegisteredUser(String status) {
            var notRegisteredUser = new RegistrationDto(getRandomLogin(),getRandomPassword(),"active");

            return notRegisteredUser;

        }

        public static RegistrationDto getBlockedUser(String status) {
            var blockedUser = new RegistrationDto(getRandomLogin(),getRandomPassword(),"blocked");
            sendRequest(blockedUser);
            return blockedUser;

        }


    }
}

