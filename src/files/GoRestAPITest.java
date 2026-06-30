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



        // Step 1: Create a new user using a dynamic timestamp email and extract the API response
        String response = given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer demo-token")
                .body(Payload.createUserWithTimestamp())
                .when().post("/users")
                .then().log().all().statusCode(201).extract().response().asString();



        // Step 2: Parse the JSON response to extract the newly created user ID
        JsonPath createUserResponse = new JsonPath(response);
        String generatedId = String.valueOf(createUserResponse.getInt("id"));



        // Step 3: Delete the created user using the extracted dynamic ID and assert 204 No Content
        given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer demo-token").body("")
                .when().delete("/users/" + generatedId)
                .then().log().all().statusCode(204);

        System.out.println("Deleted User ID: " + generatedId);



        // Step 4: Verify deletion via GET request; expecting a 404 Not Found error response
        System.out.println("Verifying deletion of User ID: " + generatedId);
        given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer demo-token").body("")
                .when().get("/users/" + generatedId)
                .then().log().all().statusCode(404).assertThat()
                .body("message", equalTo("Resource not found"));
    }
}