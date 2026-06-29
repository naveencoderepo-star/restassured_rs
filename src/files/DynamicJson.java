import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test
    public void addBook() {

        RestAssured.baseURI = "https://gorest.in";

        String response = given().header("Content-Type", "application/json")
                .body(Payload.addBook("ujassi", "17658"))
                .when().post("/public/v2/users")
                .then().assertThat().statusCode(200).extract().response().asString();


        JsonPath jsonPath = ReUsableMethods.rawStringToJsonPath(response);
        String extractedId = jsonPath.get("ID");

        System.out.println("Extracted ID: " + extractedId);


    }




}
