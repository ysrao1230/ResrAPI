package Sample;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;

public class sample {

	static String name = "Hello World";

	public static void main(String[] args) {

		System.out.println("Given String is: " + name);

	}

	@Test(priority=0)
	void Sample() {
		System.out.println("this is first testng");
	}

	@Test(priority=1)
	public void lotto_resource_returns_200_with_expected_id_and_winners() {
		System.out.println("**Starting Getting Body**");
		given().
			get("https://reqres.in/api/users?page=2").
		then().
			statusCode(200).
			body("data.id[1]", equalTo(8)).
			body("data.first_name", hasItems("Michael", "Lindsay")).
			log().
			all();
		System.out.println("**Ending  Getting Body**");

	}

	@SuppressWarnings("unchecked")
	@Test(priority=2)
	public void test2() {
		System.out.println("Starting  of posting Data");
		JSONObject request = new JSONObject();
		request.put("name", "chaya");
		request.put("job", "BA");

		System.out.println(request);
		System.out.println(request.toString());

		given().
			body(request.toJSONString()).
		when().
			post("https://reqres.in/api/users").
		then().
			statusCode(201).
			log().
			all();
		System.out.println("Ending of posting Data");

	}
	

	@Test(priority=3)
	public void xml_Validation() {
		System.out.println("************XML VALIDATION****************");
		 RestAssured.baseURI ="https://samples.openweathermap.org/data/2.5/"; 
		 RequestSpecification request = RestAssured.given();
		 
		 Response response = request.queryParam("q", "London,UK") 
		                    .queryParam("appid", "2b1fd2d7f77ccf1b7de9b441571b39b8") 
		                    .get("/weather");
		 
		 String jsonString = response.asString();
		 System.out.println(response.getStatusCode()); 
		 Assert.assertEquals(jsonString.contains("London"), true);	
		 
	}
	@SuppressWarnings("unchecked")
	@Test(priority=4)
	public void test_put() {

		JSONObject request = new JSONObject();
		request.put("name", "chaya");
		request.put("job", "BAA");

		System.out.println(request);
		System.out.println(request.toString());

		given().
			body(request.toJSONString()).
		when().
			put("https://reqres.in/api/users").
		then().statusCode(200);

	}

	@SuppressWarnings("unchecked")
	@Test(priority=5)
	public void test_patch() {

		JSONObject request = new JSONObject();
		request.put("name", "chaya");
		request.put("job", "BAA");

		System.out.println(request);
		System.out.println(request.toString());

		given().
			body(request.toJSONString()).
		when().
			patch("https://reqres.in/api/users/2").
			then().statusCode(200);

	}
	@Test(priority=6)
	public void test_delete() {

		JSONObject request = new JSONObject();
		given().
			body(request.toJSONString()).
		when().
			delete("https://reqres.in/api/users/2").
		then().statusCode(204).
			log().all();

	}

}
