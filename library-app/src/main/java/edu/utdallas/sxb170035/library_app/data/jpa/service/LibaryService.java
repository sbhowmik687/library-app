package edu.utdallas.sxb170035.library_app.data.jpa.service;

import java.util.List;
import java.util.Map;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.BookLoan;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.Borrower;
import edu.utdallas.sxb170035.library_app.data.jpa.vo.BorrowerVO;

public interface LibaryService {
	

	List<Map<String,String>> getBookDetails(String queryString);
	
	public BookLoan checkOut(String isbn,String cardId);

	Borrower createUser(BorrowerVO borrowerVO);

	void calculateFine();

	Borrower updateFine(String cardId);

	List<Map<String, String>> getBooksForCheckin(String queryString);

	public BookLoan checkIn(String isbn);

	List<Map<String, String>> getFineDetails();
	
}
