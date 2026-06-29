


public class Payload {

    public static String addPlacePayload() {

        return "{\n" +
                "  \"location\": {\n" +
                "    \"lat\": -38.383494,\n" +
                "    \"lng\": 33.427362\n" +
                "  },\n" +
                "  \"accuracy\": 50,\n" +
                "  \"name\": \"Frontline house\",\n" +
                "  \"phone_number\": \"(+91) 983 893 3937\",\n" +
                "  \"address\": \"29, side layout, cohen 09\",\n" +
                "  \"types\": [\n" +
                "    \"shoe park\",\n" +
                "    \"shop\"\n" +
                "  ],\n" +
                "  \"website\": \"http://google.com\",\n" +
                "  \"language\": \"French-IN\"\n" +
                "}";
    }

    // We are passing placeId dynamically from Add Place API response
    public static String updatePayload(String placeId) {

        return "{\n" +
                "  \"place_id\": \"" + placeId + "\",\n" +
                "  \"address\": \"70 winter walk, USA\",\n" +
                "  \"key\": \"qaclick123\"\n" +
                "}";
    }

    public static String coursePrice() {

        return "{\n" +
                "  \"dashboard\": {\n" +
                "    \"purchaseAmount\": 1162,\n" +
                "    \"website\": \"rahulshettyacademy.com\"\n" +
                "  },\n" +
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
    }

    public static String addBook() {

        return "{\n" +
                "  \"name\": \"Learn Appium Automation with Java\",\n" +
                "  \"isbn\": \"bcd\",\n" +
                "  \"aisle\": \"12127\",\n" +
                "  \"author\": \"John Doe\"\n" +
                "}";
    }

}


/*

{
        "dashboard": {
        "purchaseAmount": 910,
        "website": "rahulshettyacademy.com"
        },

        "courses": [
        {
        "title": "Selenium Python",
        "price": 50,
        "copies": 6
        },
        {
        "title": "Cypress",
        "price": 40,
        "copies": 4
        },
        {
        "title": "RPA",
        "price": 45,
        "copies": 10
        }
        ]
        }

        */
