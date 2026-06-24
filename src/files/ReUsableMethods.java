import io.restassured.path.json.JsonPath;

public class ReUsableMethods {


    public static JsonPath rawStringToJsonPath(String response) {
        JsonPath js1 = JsonPath.from(response);
       return  js1;

    }
}
