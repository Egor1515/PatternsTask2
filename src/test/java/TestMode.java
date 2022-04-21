import com.github.javafaker.Faker;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.Locale;

import static io.restassured.RestAssured.given;


public class TestMode {
    Faker faker = new Faker(new Locale("ru"));


    private final static RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(ContentType.JSON)
            .setContentType(ContentType.JSON)
            .log(LogDetail.ALL)
            .build();

    @Test

    void setUpAll() {

        given()
                .spec(requestSpec)
                .body(new RegistrationInfo(faker.name().fullName(), faker.numerify("###"),faker.expression("active")))
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }


}

