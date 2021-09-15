package datausingPOJO;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthcontrolToken {
	static RequestSpecification req;
	
	private static String uri_dev="https://ostaffuidev.onpassive.com";
	private static String uri_qa="https://hrmsapiqa.onpassive.com";

	public static String validatingToken_qa() {
		RestAssured.baseURI = uri_qa;
		 req = RestAssured.given();
		String payload = "{\r\n" + "    \"username\": \"manikanta\",\r\n"
				+ "    \"password\": \"dSTZAKwOasCxI-aslzykIQ==\"\r\n" + "}";
		req.header("Content-Type", "application/json");
		Response response = req.body(payload).post("/admin/auth/signin");
		// response.prettyPrint();
		String jsonString = response.getBody().asString();
		return JsonPath.from(jsonString).get("result.token");
	}
	
	public static String validatingToken_dev() {
		RestAssured.baseURI = uri_dev ;
		 req = RestAssured.given();
		String payload = "{\r\n" + "    \"username\": \"manikanta\",\r\n"
				+ "    \"password\": \"dSTZAKwOasCxI-aslzykIQ==\"\r\n" + "}";
		req.header("Content-Type", "application/json");
		Response response = req.body(payload).post("/admin/auth/signin");
		// response.prettyPrint();
		String jsonString = response.getBody().asString();
		return JsonPath.from(jsonString).get("result.token");
	}
	
	public RequestSpecification geturi_dev() {
		RestAssured.baseURI = uri_dev;
		return RestAssured.given().header("Authorization", "Bearer " + validatingToken_dev()).header("Content-Type", "application/json");
	}
	
	public RequestSpecification geturi_qa() {
		RestAssured.baseURI = uri_qa;
		return RestAssured.given().header("Authorization", "Bearer " + validatingToken_qa()).header("Content-Type", "application/json");
	}
}
