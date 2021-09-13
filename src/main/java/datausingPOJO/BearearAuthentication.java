package datausingPOJO;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BearearAuthentication {
	AuthcontrolToken At = new AuthcontrolToken();
	RequestSpecification req;

	@Test(priority = 0)
	public void bearerTokenAuthentication() throws FileNotFoundException {
		RestAssured.baseURI = "https://hrmsapiqa.onpassive.com";
		req = RestAssured.given();
		req.header("Authorization", "Bearer " + At.validatingToken()).header("Content-Type", "application/json");
		String addingNewDept = "{\r\n" + "  \"name\": \"hjhjh\",\r\n" + "  \"companyId\": \"mani0002\",\r\n"
				+ "  \"branchId\": 2,\r\n" + "  \"description\": \"\"\r\n" + "}";

		Response addingDept = req.body(addingNewDept).post("/admin/department");
		addingDept.prettyPrint();

		Assert.assertEquals(200, addingDept.getStatusCode());

		Response onboarding = req.when().get("/onboard/employee/branch/2");

		onboarding.prettyPrint();

		// Getting employee onboarding data

		System.out.println("******ONBOARDING DATA*********");

		Response Onboardinglist = req.headers("companyId", "9LE9b0UIKQSj4Gxfcn-wPA==").get("/onboard/employee/list");
		Onboardinglist.prettyPrint();

	}

	@Test(priority = 1)
	public void assetActions() {
		System.out.println("******************");
		RestAssured.baseURI = "https://hrmsapiqa.onpassive.com";
		req = RestAssured.given();
		req.header("Authorization", "Bearer " + At.validatingToken()).header("Content-Type", "application/json");
		String assertPyload = "{\r\n" + "  \"name\": \"hdjdj4dj\",\r\n" + "  \"companyId\": \"mani0002\",\r\n"
				+ "  \"description\": \"\"\r\n" + "}";

		Response assertpost = req.body(assertPyload).post("/admin/Assets");

		assertpost.prettyPrint();
	}

	@Test(priority = 2)
	public void jobsActions() {
		System.out.println("******************");
		RestAssured.baseURI = "https://hrmsapiqa.onpassive.com";
		req = RestAssured.given();
		req.header("Authorization", "Bearer " + At.validatingToken()).header("Content-Type", "application/json");

		// Deleting job
		String parameters = "{\"id\":17}";
		Response deleteJob = req.body(parameters).put("/admin/job/delete");
		System.out.println(deleteJob.getStatusCode());
		System.out.println(deleteJob.getStatusLine());
		System.out.println(deleteJob.getTimeIn(TimeUnit.MICROSECONDS));
		System.out.println(deleteJob.getHeader("Content-Security-Policy"));
		deleteJob.prettyPrint();
	}

	@Test(priority = 3)
	public void departmentActions() throws FileNotFoundException {
		System.out.println("******************");
		RestAssured.baseURI = "https://hrmsapiqa.onpassive.com";
		req = RestAssured.given();
		req.header("Authorization", "Bearer " + At.validatingToken()).header("Content-Type", "application/json");
		// Deprt new
		// admin/department

		File f = new File("C:\\Users\\pc\\git\\ResrAPI\\ResrAPI\\BodyData\\newDepartmentCreate.json");
		java.io.FileReader fr = new java.io.FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject jo = new JSONObject(jt);

		Response postingDept = req.body(jo.toString()).post("/admin/department");
		postingDept.prettyPrint();

		// Getting employee department data
		Response departmentlist = req.headers("companyId", "dZFanJQL1DjksMse64vaNw==").get("/admin/department/list");
		departmentlist.prettyPrint();
	}

}
