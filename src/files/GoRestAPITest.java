import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class GoRestAPITest {

    @Test
    public void createUserWithTimestampEmail() {
        System.out.println("\n========== Creating User with Timestamp Email ==========\n");

        RestAssured.baseURI = "https://gorest.in/public/v2";


        String response = given().header("Content-Type", "application/json").header("Authorization", "Bearer demo-token")

                .body(Payload.createUserWithTimestamp())
                .when().post("/users")
                .then().log().all().statusCode(201).extract().response().asString();


        JsonPath createUserResponse = new JsonPath(response);
        String generatedId = String.valueOf(createUserResponse.getInt("id"));


        given().header("Content-Type", "application/json").header("Authorization", "Bearer demo-token")

                .when().delete("/users/" + generatedId)
                .then().log().all().statusCode(204);

        System.out.println("Deleted User ID: " + generatedId);


        given().header("Content-Type", "application/json").header("Authorization", "Bearer demo-token")
                .when().get("/users/" + generatedId)
                .then().log().all().statusCode(404).assertThat().body("message", equalTo("Resource not found"));


    }


}
