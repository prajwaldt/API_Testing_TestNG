package apitesting.ecomapi;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.testng.annotations.Test;

public class EcomApi {
	@Test
	void main() {
		// login to website
		Loginsd lsd = new Loginsd();
		lsd.setUserEmail("prajwaldt1998@gmail.com");
		lsd.setUserPassword("Pr@079");

		RequestSpecification reqspec = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).build();
		LoginRes loginres = given().log().all().spec(reqspec).body(lsd)

				.when().post("/api/ecom/auth/login").then().extract().response().as(LoginRes.class);
		String tocken = loginres.getToken();
		String userid = loginres.getUserId();
		System.out.println("the tocken is : " + tocken + "\n" + "the user name is : " + userid);

		// create product
		RequestSpecification reqspec1 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", tocken).build();

		CreateProduct createproduct = given().log().all().spec(reqspec1).param("productName", "iquee")
				.param("productAddedBy", userid).param("productCategory", "productCategory")
				.param("productSubCategory", "shirt").param("productPrice", "11500")
				.param("productDescription", "new phone").param("productFor", "for all")
				.multiPart("productImage", new File("C:\\Users\\002E23744\\Documents\\nnd.png")).when()
				.post("/api/ecom/product/add-product").then().extract().response().as(CreateProduct.class);

		String productid = createproduct.getProductId();
		System.out.println("the product id is : " + productid);

		// submit the order
		RequestSpecification reqspec2 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.setContentType(ContentType.JSON).addHeader("Authorization", tocken).build();

		OrdersDetails sorc = new OrdersDetails();
		sorc.setCountry("India");
		sorc.setProductOrderedId(productid);

		// for the above class why we are creating list is its return type is list and
		// adding only one vlaue without craeteing list if we insert object it will show
		// error
		ArrayList<OrdersDetails> orderdetailslist = new ArrayList<>();
		orderdetailslist.add(sorc);

		Orders ord = new Orders();
		ord.setOrders(orderdetailslist);

		SubmitOrderRes submitors = given().log().all().spec(reqspec2).body(ord).when()
				.post("/api/ecom/order/create-order").then().extract().response().as(SubmitOrderRes.class);
		List<String> dd = submitors.getOrders();
		System.out.println("order submitted" + dd);

		// view order
		RequestSpecification reqspec3 = new RequestSpecBuilder().setBaseUri("https://rahulshettyacademy.com")
				.addHeader("Authorization", tocken).build();
		String res2 = given().log().all().spec(reqspec3).queryParam("id", dd).when()
				.get("/api/ecom/order/get-orders-details").then().extract().response().asString();
		System.out.println("the order details" + res2);

		// delete order

		String ss = given().log().all().spec(reqspec3).pathParam("productid", productid).when()
				.delete("/api/ecom/product/delete-product/{productid}").then().extract().response().asString();
		System.out.println(ss);

	}
}
