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

    public static String addBook(String isbnVal, String aisleVal) {

        return "{\n" +
                "  \"name\": \"Learn Appium Automation with Java\",\n" +
                "  \"isbn\": \"" + isbnVal + "\",\n" +
                "  \"aisle\": \"" + aisleVal + "\",\n" +
                "  \"author\": \"John iio\"\n" +
                "}";
    }

    // GoRest API - Create User with Dynamic Timestamp Email
    public static String createUserWithTimestamp() {
        String dynamicEmail = "naveen" + System.currentTimeMillis() + "@test.com";
        return "{\n" +
                "  \"name\": \"Naveen Kumar\",\n" +
                "  \"email\": \"" + dynamicEmail + "\",\n" +
                "  \"gender\": \"male\",\n" +
                "  \"status\": \"active\"\n" +
                "}";
    }

    // Create User with Dynamic Data from DataProvider
    public static String createUserWithDynamicData(String name, String gender, String status) {
        String dynamicEmail = name.toLowerCase().replace(" ", "") + System.currentTimeMillis() + "@test.com";
        return "{\n" +
                "  \"name\": \"" + name + "\",\n" +
                "  \"email\": \"" + dynamicEmail + "\",\n" +
                "  \"gender\": \"" + gender + "\",\n" +
                "  \"status\": \"" + status + "\"\n" +
                "}";
    }


}