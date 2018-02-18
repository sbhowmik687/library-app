package edu.utdallas.sxb170035.library_app.data.jpa.service;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Book;

public interface LibaryService {

	Book getBook(String queryString);
}
