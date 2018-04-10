package edu.utdallas.sxb170035.library_app.data.jpa.vo;

import javax.validation.constraints.NotNull;

public class BorrowerVO {

	@NotNull(message = "ssn name can not be null.")
	private String ssn;

	@NotNull(message = "first name can not be null.")
	private String firstName;
	
	@NotNull(message = "last name can not be null.")
	private String lastName;

	@NotNull(message = "address name can not be null.")
	private String address;

	@NotNull(message = "city name can not be null.")
	private String city;

	@NotNull(message = "state name can not be null.")
	private String state;

	@NotNull(message = "phone number can not be null.")
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
