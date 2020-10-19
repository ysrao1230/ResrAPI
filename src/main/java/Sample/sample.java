/*
 * API URL:
https://github.com/chayathilak/Test_API/tree/master/src/main/java/com/restassured/api
 *
 *Author: Y SRINIVASA RAO
 *Number: 9052880660
 *Position: QA
 *Department: Testing
 */

package Sample;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import static io.restassured.RestAssured.given;
import org.json.simple.JSONObject;
public class sample {

	static String url ="http://demo.guru99.com/V4/sinkministatement.php";
	
	static String name = "Hello World";

	public static void main(String[] args) {

		System.out.println("Given String is: " + name);

		/*String str = null;
		System.out.println(String.valueOf(str)); // This will print a String equal to "null"
		System.out.println(str.toString()); // This will throw a NullPointerException
*/	}

	@Test(priority = 1)
	public void lotto_resource_returns_200_with_expected_id_and_winners() {
		System.out.println("**Starting Getting Body**");
		given().
			get("https://reqres.in/api/users?page=2&id=12").
		then().
			statusCode(200).
			/*body("data.id[1]", equalTo(8)).
			body("data.first_name", hasItems("Michael", "Lindsay","Rachel")).*/
			log().
			all();
		System.out.println("**Ending  Getting Body**");

	}

	@SuppressWarnings("unchecked")
	@Test(priority = 2)
	public void post_data() {
		System.out.println("Starting  of posting Data");
		JSONObject request = new JSONObject();
		request.put("name", "chaya");
		request.put("job", "BA");

		System.out.println(request.toString());

		given().body(request.toJSONString()).when().post("https://reqres.in/api/users").then().statusCode(201).log()
				.all();
		System.out.println("Ending of posting Data");

	}

	@Test(priority = 3)
	public void xml_Validation() {
		System.out.println("************XML VALIDATION****************");
		RestAssured.baseURI = "https://samples.openweathermap.org/data/2.5/";
		RequestSpecification request = RestAssured.given();

		Response response = request.queryParam("q", "London,UK").queryParam("appid", "2b1fd2d7f77ccf1b7de9b441571b39b8")
				.get("/weather");

		String jsonString = response.asString();
		System.out.println(response.getStatusCode());
		Assert.assertEquals(jsonString.contains("London"), true);

	}

	@SuppressWarnings("unchecked")
	@Test(priority = 4)
	public void test_put() {

		JSONObject request = new JSONObject();
		request.put("name", "chaya");
		request.put("job", "BAA");
		System.out.println(request.toString());

		given().
			body(request.toJSONString()).
		when().
			put("https://reqres.in/api/users").
		then().
			statusCode(200);

	}

	@SuppressWarnings("unchecked")
	@Test(priority = 5)
	public void test_patch() {

		JSONObject request = new JSONObject();
		request.put("name", "chaya");
		request.put("job", "BAA");

		System.out.println(request.toString());

		given().
			body(request.toJSONString()).
		when().
			patch("https://reqres.in/api/users/2").
		then().
			statusCode(200).
			log().
			all();
	}

	@Test(priority = 6)
	public void test_delete() {

		for(int i=0;i<=2;i++) {
			
			JSONObject request = new JSONObject();
			given().
				body(request.toJSONString()).
				pathParam("value", i).
			when().
				delete("https://reqres.in/api/users/{value}").
			then().
				statusCode(204).
				log().
				all();
			System.out.println(i);
		}
		
		System.out.println("Exit program");

	}

	@Test(priority = 7)
	public void test_ResponseHeaderData_ShouldBeCorrect() {
	        
	    given().
	    when().
	        get("http://ergast.com/api/f1/2017/circuits.json").
	    then().
	        assertThat().
	        statusCode(200).
	    and().
	        contentType(ContentType.JSON).
	    and().
	        header("Content-Length",equalTo("4551")).
	        log().
	        all();
	}
	@Test(dataProvider="seasonsAndNumberOfRaces")
	public void test_NumberOfCircuits_ShouldBe20_Parameterized(String series,String season,int numberOfRaces) {      
	    given().
	    	pathParam("value", series).
	        pathParam("test",season).
	    when().
	        get("http://ergast.com/api/{value}/{test}/circuits.json").
	    then().
	        assertThat().
	        body("MRData.CircuitTable.Circuits.Location",hasSize(numberOfRaces)).log().all();
	}
	@DataProvider(name="seasonsAndNumberOfRaces")
	public Object[][] createTestDataRecords() {
	    return new Object[][] {
	        {"f1","2017",20},
	        {"f1","2016",21},
	        {"f1","1966",9},
	        {"f1","2010",19}
	    };
	}
	
	//Getting response body
	@Test
	public void getResponseBody() {
		
		
	int status_code=	given().
			queryParam("CUSTOMER_ID", "68195").
			queryParam("PASSWORD", "1234!").
			queryParam("Account_No","1").
		when().
			get("http://demo.guru99.com/V4/sinkministatement.php").getStatusCode();
	System.out.println("the status code is: " +status_code);
	System.out.println();
	given().when().get(url).then().assertThat().statusCode(200);
	}
	
	//Getting Headers
	@Test
	public static void getResponseHeaders(){
		   System.out.println("The headers in the response "+
		                   get(url).then().extract()
		           .headers());
		}
	
	//Getting response time
	@Test
	public static void getResponseTime(){
		  System.out.println("The time taken to fetch the response "+get(url)
		         .timeIn(TimeUnit.MILLISECONDS) + " milliseconds");
		}
	
	//Getting the content type
	@Test
	public static void getResponseContentType(){
		   System.out.println("The content type of response "+
		           get(url).then().extract()
		              .contentType());
		}
	public static void getSpecificPartOfResponseBody(){

		ArrayList<String> amounts = when().get(url).then().extract().path("result.statements.AMOUNT") ;
		int sumOfAll=0;
		for(String a:amounts){

		    System.out.println("The amount value fetched is "+a);
		    sumOfAll=sumOfAll+Integer.valueOf(a);
		}
		System.out.println("The total amount is "+sumOfAll);

		}
}
