package LandlordAPI;

import java.util.*;

public class Landlord {
	private String firstName;
	private String lastName;
	private boolean trusted;
	private List<Object> apartments;

	public Landlord() {

	}
	public Landlord(String FN,String LN,boolean trusted) {
		this.firstName=FN;
		this.lastName=LN;
		this.trusted=trusted;
		this.apartments = new ArrayList<Object>();
	}

	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public boolean isTrusted() {
		return trusted;
	}
	public void setTrusted(boolean trusted) {
		this.trusted = trusted;
	}
	public List<Object> getApartments() {
		return apartments;
	}
	public void setApartments(List<Object> apartments) {
		this.apartments = apartments;
	}
}