package apitesting.pojo.deserilization;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.parsing.Parser;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;

import io.restassured.response.ResponseBody;

public class OAuth {
	@Test
	void main() {
		String response =

				given()

						.formParams("client_id",
								"692183103107-p0m7ent2hk7suguv4vq22hjcfhcr43pj.apps.googleusercontent.com")
						.formParams("client_secret", "erZOWM9g3UtwNRj340YYaK_W")
						.formParams("grant_type", "client_credentials").formParams("scope", "trust").when()
						.post("https://rahulshettyacademy.com/oauthapi/oauth2/resourceOwner/token").then().log().all()
						.extract().response().asString();
		 

		JsonPath jsonPath = new JsonPath(response);
		String accessToken = jsonPath.getString("access_token");
		System.out.println(accessToken);
		Mainclass mc = given().queryParams("access_token", accessToken).when().log().all()
				.get("https://rahulshettyacademy.com/oauthapi/getCourseDetails").as(Mainclass.class);
		System.out.println(mc);
		
		System.out.println(mc.getLinkedIn());
		List<webAutomation> s=mc.getCourses().getWebAutomation();
		String str="Selenium Webdriver Java";
		for (int i=0;i<s.size();i++)
		{
			if(mc.getCourses().getWebAutomation().get(i).getCourseTitle().equalsIgnoreCase(str))
			{
				System.out.println( mc.getCourses().getWebAutomation().get(i).getPrice());
			}
		}
		

	}
}
