package apitesting.pojo.serialization;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import java.util.Arrays;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.parsing.Parser;

import io.restassured.path.json.JsonPath;

import io.restassured.response.Response;

import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class OAuth {
	@Test
	void main() {
		
Mainclasss ms=new Mainclasss();
ms.setAccuracy(50);
ms.setName("Frontline house");
ms.setPhone_number("(+91) 983 893 3937");
ms.setAddress( "29, side layout, cohen 09");
ms.setWebsite("http://google.com");
ms.setLanguage("French-IN");
ArrayList<String> sa=new ArrayList<>(Arrays.asList("shoe park","shop"));
ms.setTypes(sa);
Location ls=new Location();
ls.setLat(-38.383494);
ls.setLng(33.427362);
ms.setLocation(ls);

RequestSpecification reqspec= new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com").addQueryParam("key", "qaclick123").build();
ResponseSpecification resspec = new ResponseSpecBuilder().expectStatusCode(200) .build();
RequestSpecification res = given().log().all().spec(reqspec);
String res1=res.body(ms).when().post("/maps/api/place/add/json")
				.then().spec(resspec).extract().response().asString();
		System.out.println("the res is :" +res1);

	}
}
