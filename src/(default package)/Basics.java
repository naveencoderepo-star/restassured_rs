import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * This class validates Add Place API and Update Place API using Rest Assured.
 *
 * Scenario:
 * 1. Add a new place
 * 2. Extract place_id from Add Place response
 * 3. Use the same place_id in Update Place API
 * 4. Validate update success message
 */
public class Basics {

    public static void main(String[] args) {

        // Base URI = main domain of the API
        RestAssured.baseURI = "https://rahulshettyacademy.com";

        // Getting Add Place JSON payload from Payload class
        String addPlacePayload = Payload.addPlacePayload();

        // Step 1: Add Place API
        System.out.println("*******************  STARTED POST API CREATE NEW PLACE ID   ****************");
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
        System.out.println("*******************  END POST API CREATE NEW PLACE ID   ****************");

        // JsonPath is used to read/extract value from JSON response
        JsonPath jsonPath = new JsonPath(addPlaceResponse);

        // Extracting dynamic place_id from Add Place API response
        String placeId = jsonPath.getString("place_id");

        System.out.println("Place ID: " + placeId);

        // Step 2: Pass extracted place_id into Update payload
        String updatePayload = Payload.updatePayload(placeId);
        String newAddress = "Parrys";

        System.out.println("*******************  STARTED PUT API UPDATE NEW PLACE ID   ****************");

        // Step 3: Update Place API
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
                .body("msg", equalTo("Address successfully updated")).extract().response().asString();

        System.out.println(updateResponse);

        System.out.println("*******************  END PUT API UPDATE NEW PLACE ID   ****************");

        System.out.println("*******************  STARTED GET API USING PLACE ID   ****************");

        // GET API

        String getResponseBody = given().queryParam("key", "qaclick123").queryParam("place_id", placeId)

                .when().get("maps/api/place/get/json")
                .then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath js1 = new JsonPath(getResponseBody);
        String actualAddress = js1.getString("address");

        System.out.println("Actual address  : " + actualAddress);

    }
}