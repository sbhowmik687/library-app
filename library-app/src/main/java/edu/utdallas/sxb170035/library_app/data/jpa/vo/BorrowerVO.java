package edu.utdallas.sxb170035.library_app.data.jpa.vo;

public class BorrowerVO {

	private String ssn;

	private String bname;

	private String address;

	private String city;

	private String state;

	private String phone;

	public String getSsn() {
		return ssn;
	}

	public BorrowerVO() {
	}

	public BorrowerVO(String ssn, String bname, String address, String city, String state, String phone) {
		super();
		this.ssn = ssn;
		this.bname = bname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phone = phone;
	}

	public void setSsn(String ssn) {
		this.ssn = ssn;
	}

	public String getBname() {
		return bname;
	}

	public void setBname(String bname) {
		this.bname = bname;
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
}
