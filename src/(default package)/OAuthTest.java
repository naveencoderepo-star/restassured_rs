import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;

public class OAuthTest {


    public static void main(String[] Args) {


        /*

        given --> query params, formParams,


         */
        RestAssured.baseURI = "https://rahulshettyacademy.com";


        String response = given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type", "client_credentials")
                .formParams("scope", "trust")

                .when().log().all()
                .post("/oauthapi/oauth2/resourceOwner/token").body().asString();

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(response);

// To parse the json body we need to create object for JsonPath


        JsonPath js = new JsonPath(response);
        String accessToken = js.getString("access_token");
        String refreshToken = js.getString("refresh_token");


        // To get the course details

        GetCourse gc = given().queryParam("access_token", accessToken)
                .when().log().all()
                .get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);


        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(gc);

        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getInstructor());


    }
}
