import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * This class validates Add Place API and Update Place API using Rest Assured.
 * 
 * Scenario:
 * 1. Add a new place using POST API
 * 2. Extract place_id from Add Place response
 * 3. Update the place address using PUT API with extracted place_id
 * 4. Retrieve the updated place using GET API
 * 5. Validate that the updated address matches the expected address
 */
public class Basics {

    public static void main(String[] args) {
        
        // Set the base URI for all API calls
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Get the JSON payload for adding a new place
        String addPlacePayload = Payload.addPlacePayload();

        // ==================== STEP 1: POST API - ADD NEW PLACE ====================
        System.out.println("\n*******************  STARTED POST API - CREATE NEW PLACE  ****************");
        
        String addPlaceResponse =
                given()
                        .log().all()
                        .queryParam("key", "qaclick123")
                        .header("Content-Type", "application/json")
                        .body(addPlacePayload)
                        .when()
                        .post("/maps/api/place/add/json")
                        .then()
                        .assertThat()
                        .statusCode(200)
                        .body("scope", equalTo("APP"))
                        .extract()
                        .response()
                        .asString();

        System.out.println(addPlaceResponse);
        System.out.println("*******************  END POST API - CREATE NEW PLACE  ******************\n");

        // Extract place_id from the Add Place API response
        JsonPath jsonPath = new JsonPath(addPlaceResponse);
        String placeId = jsonPath.getString("place_id");
        System.out.println("✓ Extracted Place ID: " + placeId);

        // ==================== STEP 2: PUT API - UPDATE PLACE ADDRESS ====================
        String newAddress = "Parrys";
        System.out.println("\n*******************  STARTED PUT API - UPDATE PLACE ADDRESS  ******************");

        String updateResponse = given()
                .log().all()
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "\"place_id\":\"" + placeId + "\",\n" +
                        "\"address\":\"" + newAddress + "\",\n" +
                        "\"key\":\"qaclick123\"\n" +
                        "}")
                .when()
                .put("/maps/api/place/update/json")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .body("msg", equalTo("Address successfully updated"))
                .extract()
                .response()
                .asString();

        System.out.println(updateResponse);
        System.out.println("*******************  END PUT API - UPDATE PLACE ADDRESS  ******************\n");

        // ==================== STEP 3: GET API - RETRIEVE UPDATED PLACE ====================
        System.out.println("*******************  STARTED GET API - FETCH UPDATED PLACE  ******************");

        String getPlaceResponse = given()
                .queryParam("key", "qaclick123")
                .queryParam("place_id", placeId)
                .when()
                .get("/maps/api/place/get/json")
                .then()
                .log().all()
                .assertThat()
                .statusCode(200)
                .extract()
                .response()
                .asString();

        // ==================== STEP 4: VALIDATE UPDATED ADDRESS ====================
       JsonPath js1 = ReUsableMethods.rawStringToJsonPath(getPlaceResponse);
        String actualAddress = js1.getString("address");

        System.out.println("*******************  END GET API - FETCH UPDATED PLACE  ******************\n");
        

         System.out.println("Expected address: " + newAddress);
         System.out.println("Actual address  : " + actualAddress);


         Assert.assertEquals(newAddress, actualAddress);
         //                 Expected value, Actual Output
    }
}