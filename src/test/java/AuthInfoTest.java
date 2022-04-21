import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.specification.RequestSpecification;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;

class AuthInfoTest {
    public AuthInfo info = new AuthInfo();
    private static final RequestSpecification requestSpec = new RequestSpecBuilder()
            .setBaseUri("http://localhost")
            .setPort(9999)
            .setAccept(JSON)
            .setContentType(JSON)
            .log(LogDetail.ALL)
            .build();
    @Test

     void shouldSend(){
        given()
                .spec(requestSpec)
                .body("info.getLogin(), info.getPassword(), info.getStatus())")
                .when()
                .post("/api/system/users")
                .then()
                .statusCode(200);
    }

}