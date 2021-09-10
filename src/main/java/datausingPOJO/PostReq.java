package datausingPOJO;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class PostReq {

	public static void main(String[] args) {
		DatapostusingPOJO data = new DatapostusingPOJO();

		data.setFirsNname("Srinivasa Rao");
		data.setLastName("Yamalapalli");
		data.setId(1231);
		data.setMentorName("Ramaraju");
		data.setDesignation("Qualality Assurance");
		data.setCourceName("Data Analysis");
		
		Response rs = given()
				.contentType(ContentType.JSON)
				.body(data)
				.when()
				.post("http://localhost:3000/friends");
		System.out.println("Status code is: "+rs.getStatusCode());
		System.out.println("Data is::");
		System.out.println(rs.asString());
	}

}
