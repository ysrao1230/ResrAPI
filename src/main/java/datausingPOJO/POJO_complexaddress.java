package datausingPOJO;

import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class POJO_complexaddress {

	public static void main(String[] args) {

		POJO_complex_address add = new POJO_complex_address();
		add.setHno("22-24");
		add.setStreetName("madapur");
		add.setLocation("new mig");
		add.setCity("Hyderabad");
		add.setState("telangana");
		add.setCountry("India");
		add.setZipcode("502032");

		DatapostusingPOJO_add data_add = new DatapostusingPOJO_add();
		data_add.setId(50);
		data_add.setFirsNname("shyamala");
		data_add.setLastName("sariki");
		data_add.setMentorName("Srilakshmi");
		data_add.setDesignation("development");
		data_add.setCourceName("JAVA");
		data_add.setAddress(add);
		
		Response rs = given()
				.contentType(ContentType.JSON)
				.body(data_add)
				.when()
				.post("http://localhost:3000/friends");
		System.out.println("Status code is: "+rs.getStatusCode());
		System.out.println("Data is::");
		System.out.println(rs.asString());

	}

}
