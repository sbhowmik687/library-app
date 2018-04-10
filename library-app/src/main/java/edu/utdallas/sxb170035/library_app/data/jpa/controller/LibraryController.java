package edu.utdallas.sxb170035.library_app.data.jpa.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Book;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.BookLoan;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.Borrower;
import edu.utdallas.sxb170035.library_app.data.jpa.service.LibaryService;
import edu.utdallas.sxb170035.library_app.data.jpa.vo.BorrowerVO;

@Controller
public class LibraryController {

	@Autowired
	private LibaryService libraryService;

	@CrossOrigin
	@GetMapping("/getBooks/{bookId}")
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Map<String, String>> getBooks(String bookId) {
		return this.libraryService.getBookDetails(bookId);
	}
	@CrossOrigin
	@GetMapping("/getBooksForCheckin/{bookId}")
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Map<String, String>> getBooksForCheckin(String bookId) {
		return this.libraryService.getBooksForCheckin(bookId);
	}

	@CrossOrigin
	@GetMapping("/checkout/{bookId}")
	@ResponseBody
	@Transactional(readOnly = false)
	public ResponseEntity<BookLoan> checkOut(String bookId,
			@RequestParam(value = "cardId", required = true) String cardId) {
		BookLoan object = this.libraryService.checkOut(bookId, cardId);
		return new ResponseEntity<>(object, HttpStatus.OK);

	}
	@CrossOrigin
	@GetMapping("/checkin/{bookId}")
	@ResponseBody
	@Transactional(readOnly = false)
	public BookLoan checkIn(String bookId) {
		return this.libraryService.checkIn(bookId);
	}
	@CrossOrigin
	@PostMapping(value = "/createBorrower", headers = "Accept=application/json")
	@ResponseBody
	@Transactional(readOnly = false)
	public ResponseEntity<Borrower> createBorrower(@Valid @RequestBody BorrowerVO borrowerVO) {

		Borrower object=this.libraryService.createUser(borrowerVO);
		return new ResponseEntity<>(object, HttpStatus.OK);
		
	}

	@CrossOrigin
	@GetMapping("/calculateFine")
	@ResponseBody
	@Transactional(readOnly = false)
	public void calculateFines() {

		this.libraryService.calculateFine();
	}
	
	@CrossOrigin
	@GetMapping("/getFineDetails")
	@ResponseBody
	@Transactional(readOnly = true)
	public List<Map<String, String>> getFineDetails() {

		 return this.libraryService.getFineDetails();
	}
	@CrossOrigin
	@RequestMapping("/updateFine/{cardId}")
	@ResponseBody
	@Transactional(readOnly = false)
	public Borrower updateFine(String cardId) {
		return this.libraryService.updateFine(cardId);
	}
}
