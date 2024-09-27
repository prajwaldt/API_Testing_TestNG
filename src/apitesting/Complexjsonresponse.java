package apitesting;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.path.json.JsonPath;

public class Complexjsonresponse {
	@Test public void main() {

		JsonPath jsd = Abstract.rawtojson(Abstract.mockresponse());

		// no of courses in response use size() method and size we can use in arrays
		// only
		int count = jsd.getInt("courses.size()");
		System.out.println(count);

		// purchase amount
		String ss = jsd.getString("dashboard.purchaseAmount");
		System.out.println(ss);
		
		//print first course
		String ss1 = jsd.getString("courses[0].title");
		System.out.println(ss1);
		
		for(int i=0;i<count;i++)
		{
			String title = jsd.getString("courses["+i+"].title");
			int price = jsd.getInt("courses["+i+"].price");
			System.out.println("the course title : "+title+" and price : "+ price);
		}

		//print number of copies sold by rpa using dynamic
		for(int i=0;i<count;i++)
		{
			String title = jsd.getString("courses["+i+"].title");
			if(title.equalsIgnoreCase("RPA"))
			{
				int copies=jsd.get("courses["+i+"].copies");
				System.out.println("no of copies sold by rpa : "+copies);
				break; // no need to iterate again
			}
		}
		
		// verify the course price matches with purchase amount
		int osum=0;
		for(int i=0;i<count;i++)
		{ 
			int price = jsd.get("courses["+i+"].price");
			int copies=jsd.get("courses["+i+"].copies");
			int sum= price*copies;
			  osum=osum+sum;
			  
		}
		System.out.println(osum);
		Assert.assertEquals(osum, 1162);
		
		
	}
}
