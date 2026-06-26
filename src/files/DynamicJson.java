import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;


public class DynamicJson {

    @Test
    public void addBook() {

        RestAssured.baseURI = "http://216.10.245.166";

        String response = given().log().all().header("Content-Type", "application/json").body(Payload.addBook()).
                when().post("/Library/Addbook.php").
                then().log().all().assertThat().statusCode(200).extract().response().asString();

        System.out.println("Response: " + response);

        if (response != null && !response.isEmpty()) {
            JsonPath js = ReUsableMethods.rawStringToJsonPath(response);
            String id = js.getString("ID");
            System.out.println("✓ Extracted Book ID: " + id);
        } else {
            System.out.println("⚠ Response body is empty or null");
        }
    }
}


