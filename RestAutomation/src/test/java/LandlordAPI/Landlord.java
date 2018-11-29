package LandlordAPI;

import java.util.*;

public class Landlord { /* create POJO class from the JSON schema: firstName, lastName, trusted, apartments */
	private String firstName;  // private fields, created a string because the schema said it was a string
	private String lastName;
	private boolean trusted;
	private List<Object> apartments; // type was an array, so i decided to choose an ArrayList. This is a list of objects, any type of object

	/* default constructor for running in the backend*/
	public Landlord() {

	}
	/* constructor that takes the three arguments for the three fields in the schema. 
	 * The fourth argument is assigned to be empty by default. 
	 */
	public Landlord(String FN,String LN,boolean trusted) { // constructor, called when you create an object
		this.firstName=FN; // initialize the filed firstName of this object to the argument FN.
		this.lastName=LN;
		this.trusted=trusted;
		this.apartments = new ArrayList<Object>(); // empty ArrayList of objects.
	}

	public String getFirstName() { // getter method for firstName
		return firstName;
	}
	public void setFirstName(String firstName) { // setter method for lastName
		this.firstName = firstName;
	}
	public String getLastName() { // getter for lastName
		return lastName;
	}
	public void setLastName(String lastName) { // setter for lastName
		this.lastName = lastName;
	}
	public boolean isTrusted() { // getter  for trusted field
		return trusted;
	}
	public void setTrusted(boolean trusted) { // setter for trusted
		this.trusted = trusted;
	}
	public List<Object> getApartments() { // getter for apartments, apartment was defined to be a list of objects
		return apartments;
	}
	public void setApartments(List<Object> apartments) { // setter for apartments that take a list of objects and store it in apartments
		this.apartments = apartments;
	}
}