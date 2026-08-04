import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.config.RedirectConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class SpecBuilderTest {


    public static void main(String[] args) {

//        RestAssured.baseURI = "https://rahulshettyacademy.com/";    we are building the base url in request spec builder using setBaseUri

        AddPlace p = new AddPlace();

        p.setAccuracy(50);
        p.setAddress("206, first floor");
        p.setLanguage("Tamil");
        p.setName("Praveen");
        p.setPhone_number("74122223685");
        p.setWebsite("https://www.praveen.com");
        List<String> typeList = new ArrayList<String>();
        typeList.add("first type");
        typeList.add("second type");
        p.setTypes(typeList);
        Location l = new Location();
        l.setLat(-38.383494);
        l.setLng(33.427362);
        p.setLocation(l);

        RequestSpecification requestSpecBuilder = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com/").addQueryParam("key", "qaclick123").setBody(p).build();

        ResponseSpecification responseSpecBuilder = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        Response response = given().spec(requestSpecBuilder).body(p).config(RestAssured.config().redirect(RedirectConfig.redirectConfig().followRedirects(true))).when().post("maps/api/place/add/json").then().spec(responseSpecBuilder).extract().response();

        String responseString = response.asString();
        System.out.println(responseString);


    }
}
