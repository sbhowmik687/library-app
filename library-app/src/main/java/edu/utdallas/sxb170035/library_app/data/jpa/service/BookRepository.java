package edu.utdallas.sxb170035.library_app.data.jpa.service;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Book;

interface BookRepository extends Repository<Book, Long> {

	@Query("SELECT b.isbn,b.title,a.authorName FROM Book b, Author a WHERE b.isbn=a.book and(b.title like %:queryString% or a.authorName like %:queryString% or isbn=:queryString)")
	List<Object[]> findBookandAuthor(@Param("queryString") String queryString);

	Book findByIsbn(String isbn);

}
