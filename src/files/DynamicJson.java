import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

public class DynamicJson {

    @Test(dataProvider = "BooksData")
    public void addBook(String isbn, String aisle) {

        RestAssured.baseURI = "https://gorest.in";

        String response = given().header("Content-Type", "application/json").body(Payload.addBook(isbn, aisle))
                .when().post("/public/v2/users")
                .then().assertThat().statusCode(200).extract().response().asString();


        JsonPath jsonPath = ReUsableMethods.rawStringToJsonPath(response);
        String extractedId = jsonPath.get("ID");

        System.out.println("Extracted ID: " + extractedId);
    }

    @DataProvider(name = "BooksData")
    public Object[][] getData() {

        return new Object[][]{{"ieoheo", "8775"}, {"fhksdhf", "9886"}, {"ghgjj", "1234"}};
    }
}