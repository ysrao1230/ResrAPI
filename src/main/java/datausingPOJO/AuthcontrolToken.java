package datausingPOJO;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthcontrolToken {
	RequestSpecification req;

	public String validatingToken() {
		RestAssured.baseURI = "https://hrmsapiqa.onpassive.com";
		 req = RestAssured.given();
		String payload = "{\r\n" + "    \"username\": \"manikanta\",\r\n"
				+ "    \"password\": \"dSTZAKwOasCxI-aslzykIQ==\"\r\n" + "}";
		req.header("Content-Type", "application/json");
		Response response = req.body(payload).post("/admin/auth/signin");
		// response.prettyPrint();
		String jsonString = response.getBody().asString();
		/*
		 * String token = JsonPath.from(jsonString).get("result.token");
		 * System.out.println("Bearer token: " + token); req.header("Authorization",
		 * "Bearer " + token).header("Content-Type", "application/json");
		 */
		return JsonPath.from(jsonString).get("result.token");
	}
}
