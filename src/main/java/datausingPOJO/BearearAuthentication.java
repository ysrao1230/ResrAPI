package datausingPOJO;

import java.io.File;
import java.io.FileNotFoundException;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.internal.support.FileReader;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BearearAuthentication {

	@Test
	public void bearerTokenAuthentication() throws FileNotFoundException {
		RestAssured.baseURI = "https://hrmsapiqa.onpassive.com";
		RequestSpecification req = RestAssured.given();
		String payload = "{\r\n" + "    \"username\": \"manikanta\",\r\n"
				+ "    \"password\": \"dSTZAKwOasCxI-aslzykIQ==\"\r\n" + "}";
		req.header("Content-Type", "application/json");
		Response response = req.body(payload).post("/admin/auth/signin");
		// response.prettyPrint();
		String jsonString = response.getBody().asString();
		String token = JsonPath.from(jsonString).get("result.token");
		System.out.println("Bearer token: " + token);
		req.header("Authorization", "Bearer " + token).header("Content-Type", "application/json");

		/*
		 * String addingNewDept = "{\r\n" + "  \"name\": \"hjhjh\",\r\n" +
		 * "  \"companyId\": \"mani0002\",\r\n" + "  \"branchId\": 2,\r\n" +
		 * "  \"description\": \"\"\r\n" + "}";
		 * 
		 * Response addingDept = req.body(addingNewDept).post("/admin/department");
		 * addingDept.prettyPrint();
		 */
		// Assert.assertEquals(201, addingDept.getStatusCode());

		Response onboarding = req.when().get("/onboard/employee/branch/2");

		//onboarding.prettyPrint();

		String assertPyload = "{\r\n" + "  \"name\": \"hdjdj4dj\",\r\n" + "  \"companyId\": \"mani0002\",\r\n"
				+ "  \"description\": \"\"\r\n" + "}";

		Response assertpost = req.body(assertPyload).post("/admin/Assets");

		//assertpost.prettyPrint();
		
		
		//Deprt new
		//admin/department
		
		File f = new File("C:\\Users\\pc\\git\\ResrAPI\\ResrAPI\\BodyData\\newDepartmentCreate.json");
		java.io.FileReader fr =new java.io.FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject jo = new JSONObject(jt);
		
		String data1="{\r\n"
				+ "    \"name\": \"testing new departmentpo\",\r\n"
				+ "    \"companyId\": \"mani0002\",\r\n"
				+ "    \"branchId\": 2,\r\n"
				+ "    \"description\": \"teshd description\"\r\n"
				+ "}";
		
		Response postingDept = req.body(jo.toString()).post("/admin/department");
		postingDept.prettyPrint();
	}

}
