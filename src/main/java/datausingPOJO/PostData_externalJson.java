package datausingPOJO;

import static io.restassured.RestAssured.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.regex.Pattern;

import org.json.JSONObject;
import org.json.JSONTokener;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostData_externalJson {
	static String path = "C:/Users/ysrra/git/ResrAPI/BodyData/Friends_body.json";
	static String path2 = "C:/Users/ysrra/git/ResrAPI/BodyData/variablejson.json";

	public static void externaljsonsample() throws FileNotFoundException {
		File f = new File(path);
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject jo = new JSONObject(jt);

		Response rs = given().body(jo.toString()).contentType(ContentType.JSON).when()
				.post("http://localhost:3000/friends");

		System.out.println("Status code is: " + rs.getStatusCode());
		System.out.println("Data is: ");
		System.out.println(rs.asString());// Stroing the data
		// System.out.println(rs.jsonPath().getString("data[5].lastName"));

	}

	public static void jsonveriabledata() throws FileNotFoundException {
		File f = new File(path2);
		FileReader fr = new FileReader(f);
		JSONTokener jt = new JSONTokener(fr);
		JSONObject jo = new JSONObject(jt);

		String firstname, id, Designation, mentor, lastname;

		Scanner Variables = new Scanner(System.in);

		id = Variables.next();
		firstname = Variables.next();
		lastname = Variables.next();
		Designation = Variables.next();
		mentor = Variables.next();

		String data = jo.toString();

		data = data.replaceAll(Pattern.quote("{{" + "id" + "}}"), id);
		data = data.replaceAll(Pattern.quote("{{" + "firstname" + "}}"), firstname);
		data = data.replaceAll(Pattern.quote("{{" + "lastname" + "}}"), lastname);
		data = data.replaceAll(Pattern.quote("{{" + "designation" + "}}"), Designation);
		data = data.replaceAll(Pattern.quote("{{" + "mentor" + "}}"), mentor);

		Response rs = given().body(data).contentType(ContentType.JSON).when().post("http://localhost:3000/friends");

		System.out.println("Status code is: " + rs.getStatusCode());
		System.out.println("Data is: ");
		System.out.println(rs.asString());// Stroing the data

		/*
		 * "firstname": "{{firstname}}", "mentor": "{{mentor}}", "id": "251",
		 * "designation": "{{designation}}", "experience": "years", "lastname":
		 * "{{lastname}}"
		 */

	}

	public static void json_array_parsing() {
		Response Res = given().contentType(ContentType.JSON).when()
				.get("http://dummy.restapiexample.com/api/v1/employees");
		/*
		 * System.out.println(Res.asString());
		 * System.out.println(Res.jsonPath().get("data[20].employee_name").toString());
		 */

		JSONObject jo = new JSONObject(Res.asString());
		String EmployeeName = jo.getJSONArray("data").getJSONObject(5).get("employee_name").toString();
		System.out.println("Employee Name of the 6th employe is: " + EmployeeName);
	}

	public static void json_parsing() {
		Response Res = given().contentType(ContentType.JSON).when()
				.get("http://dummy.restapiexample.com/api/v1/employees");
		JSONObject jo = new JSONObject(Res.asString());
		for (int i = 0; i < jo.getJSONArray("data").length(); i++) {
			String Record = jo.getJSONArray("data").getJSONObject(i).get("employee_name").toString();
			System.out.println(i + " " + Record);
		}

	}

	public static void main(String[] args) throws FileNotFoundException {


		json_parsing();

	}

}
