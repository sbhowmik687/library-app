package edu.utdallas.sxb170035.library_app.data.jpa.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Book;
import edu.utdallas.sxb170035.library_app.data.jpa.service.LibaryService;

@Controller
public class LibraryController {

	@Autowired
	private LibaryService libraryService;

	@RequestMapping("/getBooks/{queryString}")
	@ResponseBody
	@Transactional(readOnly = true)
	public Book test(@PathVariable("queryString") String queryString) {
		return this.libraryService.getBook(queryString);
	}
}

