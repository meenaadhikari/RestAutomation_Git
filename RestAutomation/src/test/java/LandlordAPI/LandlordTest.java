package LandlordAPI;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.util.*;

public class LandlordTest {
	// Question1: GET Request, Check Empty, Validate, 
	@Test(enabled=false)
	public void Question1_getLandlord() {  
		given()
		.when()
		.get("http://localhost:8080/landlords")
		.then()
		.body("", is(empty()))
		.and()
		.statusCode(200)
		.extract().response().body().prettyPrint();
	}
	
	
	///Question 2
	@Test(enabled=false)
	public void Question2_postLandlord() { 
		
		Landlord landLord = new Landlord("aana","sa",true);
		// POST the new landlord
		String str=given()
				.contentType(ContentType.JSON)
				.body(landLord)
				.when()
				.post("http://localhost:8080/landlords")
				.then()
				.statusCode(201) // status code is 201
				.extract().response().body().prettyPrint();
		
		// Extract ID
		JsonPath path = new JsonPath(str);
		String ID = path.getString("id"); // returns ID
		System.out.println(ID);

		// Validate that the landlord exists via GET
		given()
		.pathParam("id", ID)
		.when()
		.get("http://localhost:8080/landlords/{id}") // Dynamic content
		.then()
		.extract().response().prettyPrint();
	}
	
	// Question 3
	@Test(enabled=true)
	public void Question3_putLandlord() {
		//POST
		Landlord landLord = new Landlord("john","KL",true);
		String str = given()
		.contentType(ContentType.JSON)
		.body(landLord)
		.when()
		.post("http://localhost:8080/landlords")
		.then()
		.statusCode(201)
		.and()
		.body("apartments", hasSize(equalTo(0)))
		.extract().response().body().prettyPrint();
		
		JsonPath path = new JsonPath(str);
		String ID = path.getString("id");
		System.out.println(ID);
		
		// PUT Request
		Landlord landLord1 = new Landlord("antony","KL",true);
		 given()
		.contentType(ContentType.JSON)
		.pathParam("id", ID)
		.body(landLord1)
		.when()
		.put("http://localhost:8080/landlords/{id}")
		.then()
		.statusCode(200)
		.extract().response().prettyPrint(); // This message contains the validation  
	}
	
	// Question 4
	@Test(enabled=false)
	public void Question4_editLandlord() {
		String ID="4DKlS34";
		Landlord landLord1 = new Landlord("saara","KL",true);
		 given()
		.contentType(ContentType.JSON)
		.pathParam("id", ID)
		.body(landLord1)
		.when()
		.put("http://localhost:8080/landlords/{id}")
		.then()
		.statusCode(404)
		.and()
		.body("message", is("There is no LandLord with id: 4DKlS34"))
		.extract().response().prettyPrint();  
	}
	
	// Question 5
	@Test(enabled=false)
	public void Question5_getLandlordFromQuestion3() {
		String ID="k49Xngpu"; // ID generated from Question3
		given()
		.pathParam("id", ID)
		.when()
		.get("http://localhost:8080/landlords/{id}")
		.then()
		.statusCode(200)
		.and()
		.body("id", is(ID))
		.and()
		.body("firstName", is("antony"))
		.and()
		.body("lastName", is("KL"))
		.extract().response().prettyPrint();
	}

}
