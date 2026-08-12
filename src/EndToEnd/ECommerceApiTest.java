package EndToEnd;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

public class ECommerceApiTest {

    public static void main(String[] args) {

        // =========================================================
        // 1. LOGIN
        // =========================================================

        RequestSpecification request = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .setContentType(ContentType.JSON)
                .build();

        LoginRequest loginRequest = new LoginRequest();

        loginRequest.setUserEmail("naveencoderepo@gmail.com");
        loginRequest.setUserPassword("Test@123");

        RequestSpecification req = given()
                .spec(request)
                .body(loginRequest);

        LoginResponsePayload loginResponsePayload = req
                .when()
                .post("/api/ecom/auth/login")
                .then()
                .log().all()
                .extract()
                .response()
                .as(LoginResponsePayload.class);

        String token = loginResponsePayload.getToken();
        String userId = loginResponsePayload.getUserId();

        System.out.println("==========================================");
        System.out.println("Generated Token : " + token);
        System.out.println("User ID         : " + userId);
        System.out.println("==========================================");


        // =========================================================
        // 2. CREATE PRODUCT
        // =========================================================

        RequestSpecification addProductBaseRequest = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token)
                .build();

        RequestSpecification responseAddProduct = given()
                .log().all()
                .spec(addProductBaseRequest)
                .param("productName", "Laptop")
                .param("productAddedBy", userId)
                .param("productCategory", "electronics")
                .param("productSubCategory", "laptop")
                .param("productPrice", "1000")
                .param("productDescription", "Lenovo Laptop")
                .param("productFor", "All")
                .multiPart(
                        "productImage",
                        new File("C:\\Users\\coher\\Downloads\\immm.jpeg")
                );

        String addProductResponse = responseAddProduct
                .when()
                .post("/api/ecom/product/add-product")
                .then()
                .log().all()
                .extract()
                .response()
                .asString();

        JsonPath js = new JsonPath(addProductResponse);

        String productId = js.getString("productId");

        System.out.println("==========================================");
        System.out.println("Product ID : " + productId);
        System.out.println("==========================================");


        // =========================================================
        // 3. CREATE ORDER
        // =========================================================

        RequestSpecification createOrderBaseReq = new RequestSpecBuilder()
                .setBaseUri("https://rahulshettyacademy.com")
                .addHeader("authorization", token)
                .setContentType(ContentType.JSON)
                .build();


        // Create OrderDetails object
        OrderDetails orderDetails = new OrderDetails();

        orderDetails.setCountry("Germany");
        orderDetails.setProductOrderedId(productId);


        // Create List<OrderDetails>
        List<OrderDetails> orderDetailsList = new ArrayList<>();

        orderDetailsList.add(orderDetails);


        // Create Orders object
        Orders orders = new Orders();

        orders.setOrders(orderDetailsList);


        // Create request
        RequestSpecification createOrderReq = given()
                .log().all()
                .spec(createOrderBaseReq)
                .body(orders);


        // Send Create Order request
        Response responseAddOrder = createOrderReq
                .when()
                .post("/api/ecom/order/create-order")
                .then()
                .log().all()
                .extract()
                .response();


        // =========================================================
        // 4. PRINT CREATE ORDER RESPONSE
        // =========================================================

        System.out.println("==========================================");
        System.out.println("CREATE ORDER RESULT");
        System.out.println("==========================================");

        System.out.println("Status Code : " + responseAddOrder.getStatusCode());
        System.out.println("Status Line : " + responseAddOrder.getStatusLine());
        System.out.println("Response    : " + responseAddOrder.asString());

        System.out.println("==========================================");
        System.out.println("Product ID  : " + productId);
        System.out.println("==========================================");

        // To delete the created product after the test, you can add a DELETE request here if needed.

        RequestSpecification deleteProductSpecReq = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addHeader("authorization", token).build();

        RequestSpecification deleteProductSpec = given().log().all().spec(deleteProductSpecReq).pathParam("productId", productId);
        String deleteProductResponse = deleteProductSpec.when().delete("/api/ecom/product/delete-product/{productId}").
                then().log().all().assertThat().statusCode(200).extract().response().asString();

        JsonPath jsDelete = new JsonPath(deleteProductResponse);
        Assert.assertEquals("Product Deleted Successfully", jsDelete.get("message"));


    }
}