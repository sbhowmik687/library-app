package edu.utdallas.sxb170035.library_app.data.jpa.service;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Book;

interface LibraryRepository extends Repository<Book, Long> {

	@Query("SELECT b FROM Book b, Author a WHERE b.isbn=a.book and(b.title like %:queryString% or a.authorName like %:queryString% or isbn=:queryString)")
	Book findBook(@Param("queryString")String queryString);

}
