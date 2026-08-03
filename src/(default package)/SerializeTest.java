import io.restassured.RestAssured;

import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;

public class SerializeTest {

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


        String response =
                given().log().all().queryParam("key", "qaclick123").body(p)
                        .when().post("/maps/api/place/add/json")
                        .then().assertThat().statusCode(200).extract().response().asString();


        System.out.println(response);


    }
}
