import io.restassured.RestAssured;
import static org.hamcrest.Matchers.equalTo;
import static io.restassured.RestAssured.given;

/**
 * This class validates the Add Place API using Rest Assured.
 *
 * Scenario:
 * 1. Set base URI
 * 2. Add query parameter API key
 * 3. Add request header
 * 4. Send JSON payload/body
 * 5. Hit POST API
 * 6. Validate response status code
 */
public class Basics {

    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String addPlacePayload = "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";

                 given().queryParam("key", "qaclick123").header("Content-Type", "application/json").body(addPlacePayload)

                .when().post("/maps/api/place/add/json")

                .then().log().all().assertThat().statusCode(200).body("scope", equalTo("APP")).headers("Server", "Apache/2.4.52 (Ubuntu)");
    }
}