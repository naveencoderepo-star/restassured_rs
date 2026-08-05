package EndToEnd;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static io.restassured.RestAssured.given;

public class ECommerceApiTest {

    public static void main(String[] args) {

        RequestSpecification request = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").setContentType(ContentType.JSON).build();
        LoginRequest loginRequest = new LoginRequest();
        loginRequest.setUserEmail("naveencoderepo@gmail.com");
        loginRequest.setUserPassword("Test@123");

        RequestSpecification req = given().spec(request).body(loginRequest);

        LoginResponsePayload loginResponsePayload = req.when().post("/api/ecom/auth/login").then().extract().response().as(LoginResponsePayload.class);

        System.out.println(loginResponsePayload.getToken());

        String token = loginResponsePayload.getToken();

        System.out.println("Created new UserID: " + loginResponsePayload.getUserId());


        // Add new product


        RequestSpecification addNewProductBase = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("Authorization",token).build();


given().log().all().spec(addNewProductBase).param("productName","Laptop").param("productAddedBy",loginResponsePayload.getUserId()).param("productCategory","Electronics").param("productSubCategory","Laptop").param("productPrice","1000").param("productDescription","Lenovo Laptop").param("productFor","All").multiPart("productImage",new File("C:\\Users\\naveen\\Downloads\\lenovo.jpg")).when().post("/api/ecom/product/add-product").then().log().all().extract().response().asString();

    }
}
