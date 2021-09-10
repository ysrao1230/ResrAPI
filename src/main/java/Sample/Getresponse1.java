package Sample;

import static io.restassured.RestAssured.*;
import io.restassured.response.Response;
import io.restassured.http.ContentType;

public class Getresponse1 {

	public static void main(String[] args) {
		Response rs = given()
				.contentType(ContentType.JSON)
				.when()
				.get("http://localhost:3000/friends");
		System.out.println("Status code is:"+rs.getStatusCode());
		System.out.println("Data is::");
		System.out.println(rs.asString());
	}

}
