
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;


public class SpecBuilderTest {


    public static void main(String[] args) {

        RestAssured.baseURI = "https://rahulshettyacademy.com/";


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


        RequestSpecification requestSpec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").setContentType(ContentType.JSON).build();
        RequestSpecification res = given().spec(requestSpec).body(p);

        ResponseSpecification responseSpec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();

        Response response = res.when().post("/maps/api/place/add/json").then().spec(responseSpec).extract().response();

        String responseString = response.asString();
        System.out.println(responseString);


    }
}
