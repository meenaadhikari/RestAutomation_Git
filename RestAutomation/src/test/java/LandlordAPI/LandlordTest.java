package LandlordAPI;
import org.testng.annotations.Test;

import com.jayway.restassured.path.json.JsonPath;
import com.jayway.restassured.http.ContentType;

import static com.jayway.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class LandlordTest {
	// GET Method
	@Test(enabled=false)
	public void getLandlord() {  
		given()
		.when()
		.get("http://localhost:8080/landlords")
		.then()
		.body("", is(empty()))
		.extract().response().body().prettyPrint();
	}
	///POST Method
	@Test(enabled=false)
	public void postLandlord() { 
		
		Landlord landLord = new Landlord("Raj","Barik",true);

		// POST
		String str=given()
				.contentType(ContentType.JSON)
				.body(landLord)
				.when()
				.post("http://localhost:8080/landlords")
				.then()
				.statusCode(201)
				//.extract().response().jsonPath().getString("id");
				.extract().response().body().prettyPrint();
		
		// Extract ID
		JsonPath path = new JsonPath(str);
		String ID = path.getString("id"); // return ID
		System.out.println(ID);

		// Get landlord
		given()
		.pathParam("id", ID)
		.when()
		.get("http://localhost:8080/landlords/{id}") // Dynamic content
		.then()
		.extract().response().body().prettyPrint();
	}
	
	@Test(enabled=false)
	public void putLandlord() {
		//POST
		Landlord landLord = new Landlord("Andrew1234PUT","Math1",true);

		String str = given()
		.contentType(ContentType.JSON)
		.body(landLord)
		.when()
		.post("http://localhost:8080/landlords")
		.then()
		.statusCode(201)
		//.extract().response().jsonPath().getString("id");
		.extract().response().body().prettyPrint();
		
		JsonPath path = new JsonPath(str);
		String ID = path.getString("id");
		System.out.println(ID);


		Landlord landLord1 = new Landlord("Andrew1234PUTUpdate","Math1",true);
		 given()
		.contentType(ContentType.JSON)
		.pathParam("id", ID)
		.body(landLord1)
		.when()
		.put("http://localhost:8080/landlords/{id}")
		.then()
		.statusCode(200)
		.extract().response().body().prettyPrint();

	}
	
	@Test(enabled=true)
	public void deleteLandlord() {
		//POST
		Landlord landLord = new Landlord("Raj","Math",true);

		String str = given()
		.contentType(ContentType.JSON)
		.body(landLord)
		.when()
		.post("http://localhost:8080/landlords")
		.then()
		.statusCode(201)
		//.extract().response().jsonPath().getString("id");
		.extract().response().body().prettyPrint();
		
		JsonPath path = new JsonPath(str);
		String ID = path.getString("id");
		System.out.println(ID);


		// delete
		Landlord landLord1 = new Landlord("Raj","Math",true);
		 given()
		.contentType(ContentType.JSON)
		.pathParam("id", ID)
		.body(landLord1)
		.when()
		.delete("http://localhost:8080/landlords/{id}")
		.then()
		.statusCode(200)
		.extract().response().body().prettyPrint();
	}

}
