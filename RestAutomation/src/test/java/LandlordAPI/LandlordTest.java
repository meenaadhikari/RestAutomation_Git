package LandlordAPI;
import org.testng.annotations.Test;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;
import java.util.*;
//import static com.jayway.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

public class LandlordTest {
	String global_ID;
	// Question1: GET Request, Check Empty, Validate, 
	@Test(enabled=false)
	public void Question1_getLandlord() {  
		given() 
		.when() 
		.get("http://localhost:8080/landlords") // GET
		.then() 
		.body("", is(empty())) // body is empty
		.and() // and 
		.statusCode(200) // check status code is 200
		.extract().response().body().prettyPrint(); 
	}
	
	
	///Question 2
	@Test(enabled=false)
	public void Question2_postLandlord() { 
		
		Landlord  landLord  = new Landlord("aana","sa",true); // POJO object named landlord. 
		
		// POST the new landlord
		String str=given() 
				.contentType(ContentType.JSON) 
				.body(landLord) 
				.when()
				.post("http://localhost:8080/landlords") 
				.then() 
				.statusCode(201) // status code is 201
				.and()
				.body(matchesJsonSchemaInClasspath("landlord-response-schema.json")) // matches schema with post
				.extract().response().prettyPrint();
		
		// we need to extract ID
		JsonPath path = new JsonPath(str); 
		String ID = path.getString("id"); 
		System.out.println(ID); 

		// GET: Validate that the landlord id exists 
		given()
		.pathParam("id", ID) // dynamic content
		.when()
		.get("http://localhost:8080/landlords/{id}") 
		.then() 
		.extract().response().prettyPrint();
	}
	
	// Question 3
	@Test(enabled=true, priority=1)
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
		
		// we need to extract ID
		JsonPath path = new JsonPath(str); 
		global_ID = path.getString("id"); // store in global_ID
		System.out.println(global_ID); 
		
		// PUT 
		Landlord landLord1 = new Landlord("antony","KL",true); 
		 given()
		.contentType(ContentType.JSON)
		.pathParam("id", global_ID) // dynamic content
		.body(landLord1) // new body for the ID.
		.when()
		.put("http://localhost:8080/landlords/{id}") 
		.then()
		.statusCode(200) // check status code to be 200, OK.
		.and()
		.body("message", is("LandLord with id: " + global_ID + " successfully updated"))
		.body(matchesJsonSchemaInClasspath("landlord-put-response.json")) // matches schema with put
		.extract().response().prettyPrint();  
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
		.statusCode(404) // status code should be error : no landlord exist for
		.and()
		.body("message", is("There is no LandLord with id: 4DKlS34"))
		.extract().response().prettyPrint();  
	}
	
	// Question 5
	@Test(enabled=false, priority=2)
	public void Question5_getLandlordFromQuestion3() {
		
		given()
		.pathParam("id", global_ID) // use global_ID from question 3
		.when()
		.get("http://localhost:8080/landlords/{id}")
		.then()
		.statusCode(200) // check status code is 200, it means it already exists, because we created in Question 3.
		.and()
		.body("id", is(global_ID)) // verify id matches with k49Xn
		.and()
		.body("firstName", is("antony"))
		.and()
		.body("lastName", is("KL"))
		.extract().response().prettyPrint();
	}

}
