import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class DataProviders {

    @Test(dataProvider = "UserData")
    public void createUserWithTimestampEmail(String name, String gender, String status) {
        System.out.println("\n========== Creating User with Timestamp Email ==========\n");
        RestAssured.baseURI = "https://gorest.in/public/v2";

        String response = given().header("Content-Type", "application/json")
                .header("Authorization", "Bearer demo-token")
                .body(Payload.createUserWithDynamicData(name, gender, status))
                .when().post("/users")
                .then().log().all().statusCode(201).extract().response().asString();

        JsonPath createUserResponse = new JsonPath(response);
        String generatedId = String.valueOf(createUserResponse.getInt("id"));
        System.out.println("Generated User ID: " + generatedId);
    }

    @DataProvider(name = "UserData")
    public Object[][] getUserData() {
        return new Object[][]{
                {"Naveen Kumar", "male", "active"},
                {"Priya Singh", "female", "active"},
                {"Rajesh Patel", "male", "inactive"}
        };
    }

}