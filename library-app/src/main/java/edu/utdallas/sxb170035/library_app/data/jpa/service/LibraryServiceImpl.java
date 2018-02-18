package edu.utdallas.sxb170035.library_app.data.jpa.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Book;


@Component("libraryService")
@Transactional
class LibraryServiceImpl implements LibaryService {

	private final LibraryRepository libraryRepository;


	@Autowired
	public LibraryServiceImpl(LibraryRepository libraryRepository) {
		this.libraryRepository = libraryRepository;
	}

	

	@Override
	public Book getBook(String queryString) {
		Assert.notNull(queryString, "QueryString must not be null");
		return this.libraryRepository.findByisbn10(queryString);
	}

}
