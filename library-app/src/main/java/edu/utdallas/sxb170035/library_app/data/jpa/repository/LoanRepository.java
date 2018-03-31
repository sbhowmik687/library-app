package edu.utdallas.sxb170035.library_app.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Book;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.BookLoan;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.Borrower;

public interface LoanRepository extends Repository<BookLoan, Long> {

	BookLoan save(BookLoan entity);

	List<Object> findByCardId(Borrower cardId);
	
	List<Object> findByCardIdAndDateInIsNull(Borrower cardId);

	BookLoan findByBook(Book book);

	List<Object[]> findAll();
	
	@Query("select loan.cardId, sum(fines.fineAmt) from BookLoan loan,Fines fines where loan.loan_id=fines.loanId and fines.paid = false and loan.dateIn is null group by loan.cardId order by loan.cardId")
	List<Object[]> getFineAmount();
	

}
