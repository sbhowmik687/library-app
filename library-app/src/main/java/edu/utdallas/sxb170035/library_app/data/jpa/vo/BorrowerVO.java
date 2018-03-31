package edu.utdallas.sxb170035.library_app.data.jpa.vo;

public class BorrowerVO {

	private String ssn;

	private String firstName;
	
	private String lastName;

	private String address;

	private String city;

	private String state;

	private String phone;

	public String getSsn() {
		return ssn;
	}

	public BorrowerVO() {
	}

	public BorrowerVO(String ssn, String bname,String lname, String address, String city, String state, String phone) {
		super();
		this.ssn = ssn;
		this.firstName = bname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phone = phone;
		this.lastName=lname;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
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

}
