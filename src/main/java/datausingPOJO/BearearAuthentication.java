package datausingPOJO;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.json.JSONObject;
import org.json.JSONTokener;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.response.Response;

public class BearearAuthentication {
	AuthcontrolToken At = new AuthcontrolToken();

	@Test(priority = 0)
	public void bearerTokenAuthentication() throws Exception {

		String addingNewDept = "{\r\n" + "  \"name\": \"hjhjh\",\r\n" + "  \"companyId\": \"mani0002\",\r\n"
				+ "  \"branchId\": 2,\r\n" + "  \"description\": \"\"\r\n" + "}";

		Response addingDept = At.geturi_qa().body(addingNewDept).post("/admin/department");
		addingDept.prettyPrint();

		Assert.assertEquals(200, addingDept.getStatusCode());

		Response onboarding = At.geturi_qa().when().get("/onboard/employee/branch/2");

		onboarding.prettyPrint();

		// Getting employee onboarding data

		System.out.println("******ONBOARDING DATA*********");

		Response Onboardinglist = At.geturi_qa().headers("companyId", "9LE9b0UIKQSj4Gxfcn-wPA==")
				.get("/onboard/employee/list");
		Onboardinglist.prettyPrint();

	}

	@Test(priority = 1)
	public void assetActions() throws Exception {

		String assertPyload = "{\r\n" + "  \"name\": \"hdjdj4dj\",\r\n" + "  \"companyId\": \"mani0002\",\r\n"
				+ "  \"description\": \"\"\r\n" + "}";

		Response assertpost = At.geturi_qa().body(assertPyload).post("/admin/Assets");

		assertpost.prettyPrint();
	}

	@Test(priority = 2)
	public void jobsActions() throws Exception {

		// Deleting job
		String parameters = "{\"id\":17}";
		Response deleteJob = At.geturi_qa().body(parameters).put("/admin/job/delete");
		System.out.println(deleteJob.getStatusCode());
		System.out.println(deleteJob.getStatusLine());
		System.out.println(deleteJob.getTimeIn(TimeUnit.MICROSECONDS));
		System.out.println(deleteJob.getHeader("Content-Security-Policy"));
		deleteJob.prettyPrint();
	}

	@Test(priority = 3)
	public void departmentActions() throws Exception {

		// Deprt new
		File f = new File("C:\\Users\\pc\\git\\ResrAPI\\ResrAPI\\BodyData\\newDepartmentCreate.json");
		java.io.FileReader fr = new java.io.FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject jo = new JSONObject(jt);

		Response postingDept = At.geturi_qa().body(jo.toString()).post("/admin/department");
		postingDept.prettyPrint();

		// Getting employee department data
		Response departmentlist = At.geturi_qa().headers("companyId", "dZFanJQL1DjksMse64vaNw==")
				.get("/admin/department/list");
		departmentlist.prettyPrint();
	}

	@Test(priority = 4)
	public void employeeResignation() throws Exception {

		// Posting Emp resignation
//		File f = new File("C:/Users/pc/git/ResrAPI/ResrAPI/BodyData/OStaff_input.json");
//		java.io.FileReader fr = new java.io.FileReader(new File("C:/Users/pc/git/ResrAPI/ResrAPI/BodyData/OStaff_input.json"));
		// JSONTokener jt = new JSONTokener(fr);
		JSONObject jo = new JSONObject(new JSONTokener(
				new java.io.FileReader(new File("C:/Users/pc/git/ResrAPI/ResrAPI/BodyData/OStaff_input.json"))));
		System.out.println(jo.get("empresign").toString());
		Response emp_resingation_post = At.geturi_dev().body(jo.get("empresign").toString())
				.post("/admin/exit/emp/resignation");

		System.out.println(emp_resingation_post.getStatusCode());
		emp_resingation_post.prettyPrint();

	}

	@Test(priority = 5)
	public void FAQs() throws Exception {

		Object string = new JSONObject(new JSONTokener(
				new java.io.FileReader(new File("C:/Users/pc/git/ResrAPI/ResrAPI/BodyData/OStaff_input.json"))))
						.get("FAQs");
		System.out.println(string);
		/*
		 * Response emp_resingation_post = req.body(jo.toString()).headers("companyId",
		 * "mani0002") .post("/admin/exit/saveSubjAndObjQuestions");
		 * 
		 * System.out.println(emp_resingation_post.getStatusCode());
		 * emp_resingation_post.prettyPrint();
		 */
		// Getting data

		Response comp_faqs = At.geturi_dev().headers("companyId", "dZFanJQL1DjksMse64vaNw==")
				.get("/admin/exit/AllExitRecordQuestions");
		comp_faqs.prettyPrint();

	}

	@Test(priority = 6)
	public void bellNotifications() throws Exception {
		System.out.println("********FAQ Post**********");
		// Posting Emp resingation

		Object string = new JSONObject(new JSONTokener(
				new java.io.FileReader(new File("C:/Users/pc/git/ResrAPI/ResrAPI/BodyData/OStaff_input.json"))))
						.get("notification_bell");
		System.out.println(string);
//		Object string = jsonObject.get("notification_bell");
		/*
		 * Response emp_resingation_post = req.body(jo.toString()).headers("companyId",
		 * "mani0002") .post("/admin/exit/saveSubjAndObjQuestions");
		 * 
		 * System.out.println(emp_resingation_post.getStatusCode());
		 * emp_resingation_post.prettyPrint();
		 */
		// Getting data

		Response notification_bell = At.geturi_dev().body(string.toString())
				.headers("companyId", "dZFanJQL1DjksMse64vaNw==")
				.post("/admin/notification/bellIconById/zaVXN6HpKqm59Q9XaAefzA%3D%3D");
		notification_bell.prettyPrint();
	}
}
