package edu.utdallas.sxb170035.library_app.data.jpa.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Book;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.BookLoan;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.Borrower;

interface LoanRepository extends Repository<BookLoan, Long> {

	BookLoan save(BookLoan entity);

	List<Object> findByCardId(Borrower cardId);

	BookLoan findByBook(Book book);

	List<Object[]> findAll();

}
