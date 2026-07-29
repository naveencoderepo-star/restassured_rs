import io.restassured.path.json.JsonPath;

import java.util.List;
import java.util.Map;

public class ComplexJsonParsing {

    public static void main(String[] args) {

        String mockCourseResponse = "{\n" +
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

        JsonPath js1 = new JsonPath(mockCourseResponse);

        // To get the course count
        int count = js1.getInt("courses.size()");
        System.out.println("Total no of courses : " + count);

        // To get the purchase amount
        int purchaseAmount = js1.getInt("dashboard.purchaseAmount");
        System.out.println("Total purchase amount : " + purchaseAmount);

        // To get the course title of first course
        String firstCourseTittle = js1.getString("courses[0].title");
        System.out.println("First course title : " + firstCourseTittle);

        // To get the all course title and it price
        for (int i = 0; i < count; i++) {
            String tittles = js1.get("courses[" + i + "].title");
            int prices = js1.get("courses[" + i + "].price");
            System.out.println("Course " + (i + 1) + ": " + tittles + " & Price: $" + prices);
        }

        // To get the copies sold by RPA course

        for (int i = 0; i < count; i++) {
            String currentTitle = js1.get("courses[" + i + "].title");

            if (currentTitle.equalsIgnoreCase("RPA")) {
                int copies = js1.getInt("courses[" + i + "].copies");
                System.out.println("Copies sold by RPA course: " + copies);
                break;
            }
        }


        // To get the overall revenue of all courses and validate it with purchase amount


    }
}


