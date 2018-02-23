package edu.utdallas.sxb170035.library_app.data.jpa.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Book;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.BookLoan;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.Borrower;
import edu.utdallas.sxb170035.library_app.data.jpa.vo.BorrowerVO;

@Component("libraryService")
@Transactional
class LibraryServiceImpl implements LibaryService {

	private final BookRepository bookRepository;
	private final BorrowerRepository borrowerRepository;
	private final LoanRepository loanRepository;

	@Autowired
	public LibraryServiceImpl(BookRepository bookRepository, BorrowerRepository borrowerRepository,
			LoanRepository loanRepository) {
		this.bookRepository = bookRepository;
		this.borrowerRepository = borrowerRepository;
		this.loanRepository = loanRepository;
	}

	@Override
	public List<Map<String, String>> getBookDetails(String queryString) {
		Assert.notNull(queryString, "QueryString must not be null");
		List<Object[]> resultList = this.bookRepository.findBookandAuthor(queryString);
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		if (null == resultList || resultList.isEmpty()) {
			return new ArrayList();
		} else {
			Map<String, String> map = new HashMap<String, String>();
			Set<String> hashSet = new HashSet<String>();
			for (Object[] resObj : resultList) {
				String isbn = (String) resObj[0];
				String title = (String) resObj[1];
				String author = (String) resObj[2];
				if (hashSet.add(isbn)) {
					map.put("isbn", isbn);
					map.put("title", title);
					map.put("author", author);

				} else {
					String authorAppend = returnList.get(returnList.size() - 1).get("author");
					authorAppend = authorAppend + " , " + author;
					returnList.get(returnList.size() - 1).put("author", authorAppend);

				}
				returnList.add(map);
			}
			return returnList;
		}

	}

	@Override
	public BookLoan checkOut(String isbn, String cardId) {
		List<Object> findByCardId = this.loanRepository.findByCardId(borrowerRepository.findByCardId(cardId));
		Book book = bookRepository.findByIsbn(isbn);
		int size = findByCardId != null ? findByCardId.size() : 0;
		if (size < 3 && null == loanRepository.findByBook(book)) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date dateIn = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // use current date.
			c.add(Calendar.DATE, 14); // Adding 14 days
			Date dueDate = c.getTime();
			Date dateOut = null;
			Borrower borrower = borrowerRepository.findByCardId(cardId);
			BookLoan entity = new BookLoan(book, borrower, dateOut != null ? sdf.format(dateOut) : null,
					sdf.format(dueDate), sdf.format(dateIn));
			return this.loanRepository.save(entity);
		}
		return new BookLoan();
	}

	@Override
	public void createUser(BorrowerVO borrowerVO) {
		if (validateUser(borrowerVO.getSsn())) {
			Borrower newBorrower = new Borrower(borrowerVO.getSsn(), borrowerVO.getBname(), borrowerVO.getAddress(),
					borrowerVO.getCity(), borrowerVO.getState(), borrowerVO.getPhone());
			this.borrowerRepository.save(newBorrower);
		}

	}

	private boolean validateUser(String ssn) {
		if (null != borrowerRepository.findBySsn(ssn))
			return false;
		else
			return true;
	}
}
