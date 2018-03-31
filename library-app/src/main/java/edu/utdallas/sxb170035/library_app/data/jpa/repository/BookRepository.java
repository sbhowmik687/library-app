package edu.utdallas.sxb170035.library_app.data.jpa.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Book;

public interface BookRepository extends Repository<Book, Long> {

	@Query("SELECT b.isbn,b.title, b.isCheckedOut, a.authorName FROM Book b, Author a WHERE b.isbn=a.book and(b.title like %:queryString% or a.authorName like %:queryString% or isbn=?#{[0]} and b.isCheckedOut=false)")
	List<Object[]> findBookandAuthor(@Param("queryString") String queryString);

	Book findByIsbn(String isbn);

	Book save(Book book);

	@Query("select b.isbn,b.title,brw.bname,loan.dateIn from Book b, Borrower brw, BookLoan loan where b.isbn=loan.book and loan.cardId=brw.cardId and (brw.bname like %:queryString% or b.isbn=?#{[0]} or brw.cardId=?#{[0]})")
	List<Object[]> findBookByCardIdorBrwrNameorIsbn(@Param("queryString") String queryString);

}
