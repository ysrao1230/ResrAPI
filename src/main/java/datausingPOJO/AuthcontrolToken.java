package datausingPOJO;

import java.io.File;
import java.io.FileNotFoundException;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class AuthcontrolToken {
	static RequestSpecification req;

	private static String uri_dev = "https://ostaffapidev.onpassive.com";
	private static String uri_qa = "https://hrmsapiqa.onpassive.com";

	public static String validatingToken_qa() throws Exception {
		RestAssured.baseURI = uri_qa;
		req = RestAssured.given();
		JSONObject jo = new JSONObject(new JSONTokener(
				new java.io.FileReader(new File("C:/Users/pc/git/ResrAPI/ResrAPI/BodyData/OStaff_input.json"))));
		System.out.println(jo.get("empresign").toString());
		Response response = req.body(jo).post("/admin/auth/signin");
		// response.prettyPrint();
		String jsonString = response.getBody().asString();
		return JsonPath.from(jsonString).get("result.token");
	}

	public static String validatingToken_dev() throws Exception {
		RestAssured.baseURI = uri_dev;
		req = RestAssured.given();
		JSONObject jo = new JSONObject(new JSONTokener(
				new java.io.FileReader(new File("C:/Users/pc/git/ResrAPI/ResrAPI/BodyData/OStaff_input.json"))));
		System.out.println(jo.get("login").toString());
		Response response = req.body(jo).post("/admin/auth/signin");
		// response.prettyPrint();
		String jsonString = response.getBody().asString();
		return JsonPath.from(jsonString).get("result.token");
	}

	public RequestSpecification geturi_dev() throws Exception {
		RestAssured.baseURI = uri_dev;
		return RestAssured.given().header("Authorization", "Bearer " + validatingToken_dev()).header("Content-Type",
				"application/json");
	}

	public RequestSpecification geturi_qa() throws Exception {
		RestAssured.baseURI = uri_qa;
		return RestAssured.given().header("Authorization", "Bearer " + validatingToken_qa()).header("Content-Type",
				"application/json");
	}
}
