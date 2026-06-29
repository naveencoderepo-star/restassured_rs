import io.restassured.RestAssured;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * GoRest API - Testing with Timestamp-based Dynamic Email
 * API: https://gorest.in/public/v2/users
 * Authorization: Bearer demo-token
 */
public class GoRestAPITest {

    @Test
    public void createUserWithTimestampEmail() {
        System.out.println("\n========== Creating User with Timestamp Email ==========\n");

        RestAssured.baseURI = "https://gorest.in/public/v2";


        String response = given().header("Content-Type", "application/json").header("Authorization", "Bearer demo-token")

                .body(Payload.createUserWithTimestamp())
                .when().post("/users")
                .then().log().all().statusCode(201).extract().response().asString();


        System.out.println("Newly created user : " + response);


    }


}
