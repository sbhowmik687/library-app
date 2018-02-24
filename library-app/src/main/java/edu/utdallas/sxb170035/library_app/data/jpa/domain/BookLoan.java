package edu.utdallas.sxb170035.library_app.data.jpa.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.springframework.util.Assert;

@Entity
public class BookLoan implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long loan_id;

	@ManyToOne(optional = false)
	@JoinColumn(name = "loan_isbn")
	private Book book;

	@ManyToOne(optional = false)
	@JoinColumn(name = "card_id", nullable = false)
	private Borrower cardId;

	@Column(name = "date_out", nullable = true)
	private String dateOut;

	@Column(name = "due_date", nullable = false)
	private String dueDate;

	@Column(name = "date_in", nullable = false)
	private String dateIn;

	public BookLoan() {
	}

	public BookLoan(Book book, Borrower cardId, String dateOut, String dueDate, String dateIn) {
		super();
		this.book = book;
		this.cardId = cardId;
		this.dateOut = dateOut;
		this.dueDate = dueDate;
		this.dateIn = dateIn;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Long getLoan_id() {
		return loan_id;
	}

	public Book getBook() {
		return book;
	}

	public Borrower getCardId() {
		return cardId;
	}

	public String getDateOut() {
		return dateOut;
	}

	public String getDueDate() {
		return dueDate;
	}

	public String getDateIn() {
		return dateIn;
	}

	@Override
	public String toString() {
		return "BookLoan [loan_id=" + loan_id + ", book=" + book + ", cardId=" + cardId + ", dateOut=" + dateOut
				+ ", dueDate=" + dueDate + ", dateIn=" + dateIn + "]";
	}

	
}
