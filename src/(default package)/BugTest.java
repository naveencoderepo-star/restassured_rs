import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import java.io.File;
import java.util.Base64;

import static io.restassured.RestAssured.given;

public class BugTest {

    public static void main(String[] args) {

        // ==========================================
        // 1. INITIAL SETUP & CONFIGURATION
        // ==========================================

        RestAssured.baseURI = "https://naveendocuments777.atlassian.net/";

        String email = "naveendocuments777@gmail.com";
        String apiToken = System.getenv("ATLASSIAN_API_TOKEN"); // Load from environment variable

        // Generate Base64 Authorization Header
        String rawAuth = email + ":" + apiToken;
        String authHeader = "Basic " + Base64.getEncoder().encodeToString(rawAuth.getBytes());

        // Payload for creating issue (Using Java Text Block for readability)
        String issuePayload = """
                {
                  "fields": {
                    "project": {
                      "key": "SCRUM"
                    },
                    "summary": "Bug with attachment checks - Internal Server Error during user checkout",
                    "description": "Steps to reproduce: 1. Add item to cart. 2. Proceed to checkout. Expected: Order confirmation. Actual: 500 Error.",
                    "issuetype": {
                      "name": "Bug"
                    }
                  }
                }
                """;

        // ==========================================
        // 2. CREATE A NEW ISSUE (POST)
        // ==========================================

        String createResponseBody =
                given()
                        .header("Authorization", authHeader)
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .body(issuePayload)
                        .when()
                        .post("/rest/api/2/issue")
                        .then()
                        .assertThat().statusCode(201)
                        .extract().response().asString();

        // Parse and extract the Issue ID
        JsonPath js = new JsonPath(createResponseBody);
        String issueId = js.getString("id");
        System.out.println(">>> Issue created successfully. Issue ID: " + issueId);


        // ==========================================
        // 3. ATTACH FILE TO ISSUE (POST MULTIPART)
        // ==========================================

        File fileToUpload = new File("C:\\Users\\coher\\Pictures\\Pictures\\ShotBuzz\\Issue#13.png");

        given()
                .header("Authorization", authHeader)
                .header("X-Atlassian-Token", "no-check")
                .header("Accept", "application/json")
                .pathParam("key", issueId)
                .multiPart("file", fileToUpload)
                .when()
                .post("/rest/api/2/issue/{key}/attachments")
                .then()
                .assertThat().statusCode(200);

        System.out.println(">>> Attachment uploaded successfully to Issue ID: " + issueId);


        // ==========================================
        // 4. FETCH ISSUE DETAILS (GET)
        // ==========================================

        String issueDetails =
                given()
                        .header("Authorization", authHeader)
                        .header("Content-Type", "application/json")
                        .header("Accept", "application/json")
                        .pathParam("key", issueId)
                        .when()
                        .get("/rest/api/2/issue/{key}")
                        .then()
                        .log().all()
                        .assertThat().statusCode(200)
                        .extract().response().asString();

        System.out.println("==============================================================================");
        System.out.println("Fetched Issue Details: \n" + issueDetails);
    }
}
