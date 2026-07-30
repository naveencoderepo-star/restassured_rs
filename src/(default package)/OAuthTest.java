import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import org.junit.Assert;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import static io.restassured.RestAssured.*;


public class OAuthTest {

    public static <Api> void main(String[] Args) {

        String[] courseTitles = {"Selenium Webdriver Java", "Cypress", "Protractor"};

        RestAssured.baseURI = "https://rahulshettyacademy.com";

        String response = given().formParams("client_id", "692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
                .formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
                .formParams("grant_type", "client_credentials")
                .formParams("scope", "trust")

                .when().log().all()
                .post("/oauthapi/oauth2/resourceOwner/token").body().asString();

        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(response);

        JsonPath js = new JsonPath(response);
        String accessToken = js.getString("access_token");
        String refreshToken = js.getString("refresh_token");


        GetCourse gc = given().queryParam("access_token", accessToken).when().log().all().get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(GetCourse.class);


        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println(gc);
        System.out.println(gc.getLinkedIn());
        System.out.println(gc.getInstructor());
        System.out.println("+++++++++++++++++++++++++++++++++++++++++++++++++++++++");
        System.out.println("Price of course title : " + gc.getCourses().getWebAutomation().getFirst().getCourseTitle());


        ArrayList<String> arrayList = new ArrayList<String>();

        List<WebAutomation> webCourses = gc.getCourses().getWebAutomation();

        for (int i = 0; i < webCourses.size(); i++) {

            arrayList.add(webCourses.get(i).getCourseTitle());
        }

        List<String> expectedList = Arrays.asList(courseTitles);

        Assert.assertTrue(arrayList.equals(expectedList));
    }
}
