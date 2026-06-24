import io.restassured.path.json.JsonPath;
import java.util.List;
import java.util.Map;

public class ComplexJsonParsing {

    public static void main(String[] args) {

        String mockCourseResponse = "{\n" +
                "  \"courses\": [\n" +
                "    {\n" +
                "      \"title\": \"Selenium Python\",\n" +
                "      \"price\": 50,\n" +
                "      \"copies\": 6\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Cypress\",\n" +
                "      \"price\": 40,\n" +
                "      \"copies\": 4\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"RPA\",\n" +
                "      \"price\": 45,\n" +
                "      \"copies\": 10\n" +
                "    },\n" +
                "    {\n" +
                "      \"title\": \"Appium\",\n" +
                "      \"price\": 36,\n" +
                "      \"copies\": 7\n" +
                "    }\n" +
                "  ]\n" +
                "}";

        JsonPath js1 = new JsonPath(mockCourseResponse);
        
        List<Map<String, Object>> courses = js1.getList("courses");
        int courseCount = courses.size();
        
        System.out.println("Total number of courses: " + courseCount);
        
        for (int i = 0; i < courseCount; i++) {
            String title = js1.getString("courses[" + i + "].title");
            int price = js1.getInt("courses[" + i + "].price");
            int copies = js1.getInt("courses[" + i + "].copies");
            System.out.println("Course " + (i + 1) + ": " + title + " | Price: $" + price + " | Copies: " + copies);
        }
    }
}

