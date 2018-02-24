package edu.utdallas.sxb170035.library_app.data.jpa.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class Fines implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@OneToOne(optional = false)
	@JoinColumn(name = "loan_id")
	private BookLoan loanId;

	private float fineAmt;

	@Column(name = "paid", nullable = false)
	private boolean paid;

	public Fines() {
	}
	public Fines(BookLoan loanId, float fineAmt, boolean paid) {
		super();
		this.loanId = loanId;
		this.fineAmt = fineAmt;
		this.paid = paid;
	}
	public BookLoan getLoanId() {
		return loanId;
	}

	public void setLoanId(BookLoan loanId) {
		this.loanId = loanId;
	}

	public float getFineAmt() {
		return fineAmt;
	}

	public void setFineAmt(float fineAmt) {
		this.fineAmt = fineAmt;
	}

	public boolean isPaid() {
		return paid;
	}

	public void setPaid(boolean paid) {
		this.paid = paid;
	}

}
