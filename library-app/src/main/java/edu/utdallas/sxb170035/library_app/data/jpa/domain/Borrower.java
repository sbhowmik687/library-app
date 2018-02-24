package edu.utdallas.sxb170035.library_app.data.jpa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.util.Assert;

@Entity
public class Borrower implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.TABLE)
	private String cardId;

	@Column(name = "ssn", nullable = false)
	private String ssn;

	@Column(name = "bname", nullable = false)
	private String bname;

	@Column(name = "address", nullable = false)
	private String address;

	@Column(name = "city", nullable = false)
	private String city;

	@Column(name = "state", nullable = false)
	private String state;

	@Column(name = "phone", nullable = false)
	private String phone;

	public Borrower() {
	}

	public Borrower(String ssn, String bname, String address, String city, String state, String phone) {
		super();
		this.ssn = ssn;
		this.bname = bname;
		this.address = address;
		this.city = city;
		this.state = state;
		this.phone = phone;
	}

	public String getCity() {
		return city;
	}

	public String getState() {
		return state;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getCardId() {
		return cardId;
	}

	public String getSsn() {
		return ssn;
	}

	public String getBname() {
		return bname;
	}

	public String getAddress() {
		return address;
	}

	public String getPhone() {
		return phone;
	}

	@Override
	public String toString() {
		return "Borrower [card_id=" + cardId + ", ssn=" + ssn + ", bname=" + bname + ", address=" + address + ", phone="
				+ phone + "]";
	}

}
