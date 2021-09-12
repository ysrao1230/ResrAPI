package datausingPOJO;

import static io.restassured.RestAssured.given;

import org.json.JSONArray;
import org.json.JSONObject;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class POST_data_simpleJson {

	public static void main(String[] args) {

		JSONArray address_information = new JSONArray();

		JSONObject address_info = new JSONObject();
		address_info.put("Hno", "125-85/125");
		address_info.put("City", "Hyderbad");
		address_info.put("State", "Telanagana");
		address_info.put("Country", "India");
		address_info.put("ZipCode", 502032);

		JSONObject Second_address_info = new JSONObject();
		Second_address_info.put("Hno", "125/A");
		Second_address_info.put("City", "Visakahpatnam");
		Second_address_info.put("State", "Andhrapradesh");
		Second_address_info.put("Country", "India");
		Second_address_info.put("ZipCode", 535546);

		address_information.put(0, address_info);
		address_information.put(1, Second_address_info);

		JSONObject obj = new JSONObject();
		obj.put("FirstName", "Radhakrishna");
		obj.put("LastName", "Reddy");
		obj.put("Cource", "Motor_operation");
		obj.put("Mentor", "BS mohan krishna");
		obj.put("Department", "Applied Physics");
		obj.put("id", 57);
		obj.put("Address", address_information);

		Response rs = given().contentType(ContentType.JSON).body(obj.toString()).when()
				.post("http://localhost:3000/friends");
		System.out.println("Status code is: " + rs.getStatusCode());
		System.out.println("Data is::");
		System.out.println(rs.asString());

	}

}
