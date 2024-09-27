package apitesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import org.testng.Assert;

public class Basics {

	public static void main(String[] args) {
		// there are three methods
		// given-provide all inputs
		// when-submit the request(resourse and httpmethod)
		// then-validate the response

		// add place
		System.out.println("**********post method");
		RestAssured.baseURI = "https://rahulshettyacademy.com";
		String response = given().log().all().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body(Abstract.addplace()).when().post("/maps/api/place/add/json").then().log().all().assertThat()
				.statusCode(200).body("scope", equalTo("APP")).header("Server", "Apache/2.4.52 (Ubuntu)").extract()
				.response().asString();// extracting res into string and stroting into string variable called response

		// extract the res body into normal string for getting placeid value
		// use JsonPath class and create object pass the res string as parameter then
		// string will convert to json form
		JsonPath js = new JsonPath(response);

		String placeid = js.getString("place_id"); // inside getstring method u need to pass the path

		// update place
		System.out.println("**************update method");

		String address = "70 winter walk, USA";
		given().queryParam("key", "qaclick123").header("Content-Type", "application/json")
				.body("{\r\n" + "\"place_id\":\"" + placeid + "\",\r\n" + "\"address\":\"" + address + "\",\r\n"
						+ "\"key\":\"qaclick123\"\r\n" + "}")
				.when().put("/maps/api/place/update/json").then().log().all().assertThat().statusCode(200)
				.body("msg", equalTo("Address successfully updated"));

		// get place no need to send any other detail except url
		System.out.println("**************get method");

		String getresponse = given().queryParam("key", "qaclick123").queryParam("place_id", placeid).when()
				.get("maps/api/place/get/json").then().assertThat().statusCode(200).extract().response().asString();
		System.out.println("the resp is " + getresponse);

		JsonPath js1 = Abstract.rawtojson(getresponse); // calling rawtojson static method in andor class using
														// class.method storing return object in jsonpath object type

		String currentaddress = js1.getString("address");
		System.out.println(currentaddress);
		Assert.assertEquals(address, currentaddress);// testng librery need to add for assertion

	}

}
