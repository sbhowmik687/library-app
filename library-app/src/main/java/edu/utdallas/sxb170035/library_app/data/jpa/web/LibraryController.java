package edu.utdallas.sxb170035.library_app.data.jpa.web;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Book;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.BookLoan;
import edu.utdallas.sxb170035.library_app.data.jpa.service.LibaryService;
import edu.utdallas.sxb170035.library_app.data.jpa.vo.BorrowerVO;

@Controller
public class LibraryController {

	@Autowired
	private LibaryService libraryService;

	@RequestMapping("/getBooks/{queryString}")
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Map<String,String>> getBooks(@PathVariable("queryString") String queryString) {
		return this.libraryService.getBookDetails(queryString);
	}
	
	@RequestMapping("/checkout/{bookId}")
	@ResponseBody
	@Transactional(readOnly = false)
	public BookLoan checkOut(@PathVariable("bookId") String bookId,@RequestParam(value = "cardId", required = true) String cardId) {
		return this.libraryService.checkOut(bookId,cardId);
	}
	
	@RequestMapping(value="/createBorrower", method = RequestMethod.POST)
	@ResponseBody
	@Transactional(readOnly = false)
	public void createBorrower(@RequestBody BorrowerVO borrowerVO) {
		
		this.libraryService.createUser(borrowerVO);
	}
}

