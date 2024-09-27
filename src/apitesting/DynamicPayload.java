package apitesting;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import static io.restassured.RestAssured.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
 
public class DynamicPayload {

//	@DataProvider(name="booksdata")
//	public  Object[][] datadrive()
//	{
//		return new Object[][] {{"flat","123"},{"space","456"},{"edge","0987"}};
//	}
//	
//	
//	@Test(dataProvider="booksdata")
//	public void main(String ss,String nn)
//	{
//		 RestAssured.baseURI="http://216.10.245.166";
//		String res= given().header("Content-Type","application/json")
//				.body(Abstract.dynamicpload(ss,nn)) // pass the argument and dynamically add values to body
//				.when().post("Library/Addbook.php")
//				.then().assertThat().statusCode(200).extract().response().asString();
//	JsonPath js=Abstract.rawtojson(res);
//	String s1=js.get("ID");
//	System.out.println(s1);
//
//	}
	
	@Test
	void main() throws IOException
	{
		 RestAssured.baseURI="http://216.10.245.166";
			  given().log().all().header("Content-Type","application/json")
					.body(new String(Files.readAllBytes(Paths.get("C:\\Users\\002E23744\\Downloads\\hh.json")))).when().post("Library/Addbook.php")
					.then();
	}
	
	

}
