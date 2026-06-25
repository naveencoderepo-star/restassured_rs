import io.restassured.path.json.JsonPath;
import org.testng.Assert;
import org.testng.annotations.Test;

public class SumValidation {

    @Test
    public void sumOfCourses() {

        String payLoad = "{\n" +
                "        \"dashboard\": {\n" +
                "        \"purchaseAmount\": 910,\n" +
                "        \"website\": \"rahulshettyacademy.com\"\n" +
                "        },\n" +
                "\n" +
                "        \"courses\": [\n" +
                "        {\n" +
                "        \"title\": \"Selenium Python\",\n" +
                "        \"price\": 50,\n" +
                "        \"copies\": 6\n" +
                "        },\n" +
                "        {\n" +
                "        \"title\": \"Cypress\",\n" +
                "        \"price\": 40,\n" +
                "        \"copies\": 4\n" +
                "        },\n" +
                "        {\n" +
                "        \"title\": \"RPA\",\n" +
                "        \"price\": 45,\n" +
                "        \"copies\": 10\n" +
                "        }\n" +
                "        ]\n" +
                "        }";

        JsonPath js1 = new JsonPath(payLoad);
        int count = js1.getInt("courses.size()");
        int originalPurchaseAmount = js1.getInt("dashboard.purchaseAmount");

        int initialSum = 0;

        for (int i = 0; i < count; i++) {
            int prices = js1.getInt("courses[" + i + "].price");
            int copies = js1.getInt("courses[" + i + "].copies");
            int totalAmount = prices * copies;

//            System.out.println(totalAmount);

            initialSum = initialSum + totalAmount;


        }

        System.out.println(initialSum);
        Assert.assertEquals(initialSum, originalPurchaseAmount, "The calculated sum of all courses match the original purchase amount.");
    }

}
