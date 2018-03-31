package edu.utdallas.sxb170035.library_app.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

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
	@JoinColumn(name = "card_id", nullable = true)
	private Borrower cardId;

	@Column(name = "date_out", nullable = false)
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

	public void setLoan_id(Long loan_id) {
		this.loan_id = loan_id;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public void setCardId(Borrower cardId) {
		this.cardId = cardId;
	}

	public void setDateOut(String dateOut) {
		this.dateOut = dateOut;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

	public void setDateIn(String dateIn) {
		this.dateIn = dateIn;
	}

	@Override
	public String toString() {
		return "BookLoan [loan_id=" + loan_id + ", book=" + book + ", cardId=" + cardId + ", dateOut=" + dateOut
				+ ", dueDate=" + dueDate + ", dateIn=" + dateIn + "]";
	}

	
}
