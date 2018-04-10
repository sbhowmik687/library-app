package edu.utdallas.sxb170035.library_app.data.jpa.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Book;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.BookLoan;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.Borrower;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.Fines;
import edu.utdallas.sxb170035.library_app.data.jpa.repository.BookRepository;
import edu.utdallas.sxb170035.library_app.data.jpa.repository.BorrowerRepository;
import edu.utdallas.sxb170035.library_app.data.jpa.repository.FineRepository;
import edu.utdallas.sxb170035.library_app.data.jpa.repository.LoanRepository;
import edu.utdallas.sxb170035.library_app.data.jpa.vo.BorrowerVO;
import edu.utdallas.sxb170035.library_app.data.jpa.controller.exception.ApplicationException;


@Component("libraryService")
@Transactional
class LibraryServiceImpl implements LibaryService {

	private final BookRepository bookRepository;
	private final BorrowerRepository borrowerRepository;
	private final LoanRepository loanRepository;
	private final FineRepository fineRepository;

	@Autowired
	public LibraryServiceImpl(BookRepository bookRepository, BorrowerRepository borrowerRepository,
			LoanRepository loanRepository, FineRepository fineRepository) {
		this.bookRepository = bookRepository;
		this.borrowerRepository = borrowerRepository;
		this.loanRepository = loanRepository;
		this.fineRepository = fineRepository;
	}

	@Override
	public List<Map<String, String>> getBookDetails(String queryString) {
		Assert.notNull(queryString, "QueryString must not be null");
		List<Object[]> resultList = this.bookRepository.findBookandAuthor(queryString);
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		if (null == resultList || resultList.isEmpty()) {
			throw new ApplicationException("500", "Book not found");
		} else {
			Set<String> hashSet = new HashSet<String>();
			for (Object[] resObj : resultList) {
				String isbn = (String) resObj[0];
				String title = (String) resObj[1];
				Boolean isCheckedOut = (Boolean) resObj[2];
				String available;
				if (isCheckedOut) {
					available = "no";
				} else {
					available = "yes";
				}
				String author = (String) resObj[3];
				if (hashSet.add(isbn)) {
					map = new HashMap<String, String>();
					map.put("isbn", isbn);
					map.put("title", title);
					map.put("available", available);
					map.put("author", author);
					returnList.add(map);

				} else {
					StringBuffer authorAppend = new StringBuffer(returnList.get(returnList.size() - 1).get("author"));
					authorAppend.append(",");
					authorAppend.append(author);
					returnList.get(returnList.size() - 1).put("author", authorAppend.toString());

				}
			}
			return returnList;
		}

	}
	@Override
	public List<Map<String, String>> getBooksForCheckin(String queryString) {
		borrowerRepository.findByCardId(queryString);
		Assert.notNull(queryString, "QueryString must not be null");
		List<Object[]> resultList = this.bookRepository.findBookByCardIdorBrwrNameorIsbn(queryString);
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		if (null == resultList || resultList.isEmpty()) {
			throw new ApplicationException("500", "Book not found");
		} else {
			for (Object[] resObj : resultList) {
				String isbn = (String) resObj[0];
				String title = (String) resObj[1];
				String name = (String) resObj[2];
				String dateIn = (String) resObj[3];
				if(null==dateIn)
				{
					map = new HashMap<String, String>();
					map.put("isbn", isbn);
					map.put("title", title);
					map.put("name", name);
					returnList.add(map);
				}
				

				
			}
			return returnList;
		}
	}

	@Override
	public BookLoan checkOut(String isbn, String cardId) {
		List<Object> findByCardId = this.loanRepository.findByCardIdAndDateInIsNull(borrowerRepository.findByCardId(cardId));
		Book book = bookRepository.findByIsbn(isbn);
		int size = findByCardId != null ? findByCardId.size() : 0;
		if (size < 3 && (null != book && !book.isCheckedOut())) {
			SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
			Date dateOut = new Date();
			Calendar c = Calendar.getInstance();
			c.setTime(new Date()); // use current date.
			c.add(Calendar.DATE, 14); // Adding 14 days
			Date dueDate = c.getTime();
			Date dateIn = null;
			Borrower borrower = borrowerRepository.findByCardId(cardId);
			BookLoan entity = new BookLoan(book, borrower, dateOut != null ? sdf.format(dateOut) : null,
					sdf.format(dueDate), dateIn != null ? sdf.format(dateIn) : null);
			book.setCheckedOut(true);
			bookRepository.save(book);
			return this.loanRepository.save(entity);
		}
		else
		{
			throw new ApplicationException("500", "User not allowed to check out more books");
		}
	}
	@Override
	public BookLoan checkIn(String isbn) {
		Book book = bookRepository.findByIsbn(isbn);
		BookLoan bookLoan = this.loanRepository.findByBook(book);
		Fines fine = fineRepository.findByLoanId(bookLoan);
		if (null != fine) {
			fine.setPaid(true);
			fineRepository.save(fine);
		}
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date dateIn = new Date();
		bookLoan.setDateIn(sdf.format(dateIn));
		book.setCheckedOut(false);
		bookRepository.save(book);
		return this.loanRepository.save(bookLoan);
	}

	@Override
	public Borrower createUser(BorrowerVO borrowerVO) {
		String ssn=borrowerVO.getSsn();
		ssn=ssn.substring(0, 3)+"-"+ssn.substring(3,5)+"-"+ssn.substring(5);//converting to format 850-47-3740
		if (validateUser(ssn)) {
			Borrower newBorrower = new Borrower(ssn, borrowerVO.getLastName(),borrowerVO.getFirstName(), borrowerVO.getAddress(),
					borrowerVO.getCity(), borrowerVO.getState(), borrowerVO.getPhone());
			return this.borrowerRepository.save(newBorrower);
		}
		else
		{
			throw new ApplicationException("500", "Error in creating user.");
		}

	}

	private boolean validateUser(String ssn) {
		if (null != borrowerRepository.findBySsn(ssn))
			return false;
		else
			return true;
	}

	@Override
	public void calculateFine() {
		List<Object[]> resultList = this.loanRepository.findAll();
		for (Object obj : resultList) {
			BookLoan bookLoan = (BookLoan) obj;
			Fines fine = fineRepository.findByLoanId(bookLoan);
			if (null == fine) {
				fine = new Fines();

			} else if (fine.isPaid()) {
				continue;
			} 
				updateFines(fine, bookLoan);
		}

	}

	private double updateFines(Fines fine, BookLoan bookLoan) {
		Date due_date = null;
		Date date_out = null;
		Date date_in = null;
		String dateIn = bookLoan.getDateIn() ;
		String dueDate = bookLoan.getDueDate();
		String dateOut = bookLoan.getDateOut();
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		try {
			due_date = sdf.parse(dueDate);
			date_out = sdf.parse(dateOut);
			if(null!=dateIn)
			date_in = sdf.parse(dateIn);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double fineAmt = 0;
		if (null == dateIn) {
			 
				fineAmt = (calculateDaysOut(due_date, new Date())) * 0.25;
			
		} else {
			if (calculateDaysOut(date_out,date_in) > 14) {
				fineAmt = (calculateDaysOut(due_date, date_in)) * 0.25;
			}
		}
		fine.setFineAmt((float)fineAmt);
		fine.setLoanId(bookLoan);
		fine.setPaid(false);
		fineRepository.save(fine);
		return fineAmt;
	}

	private int calculateDaysOut(Date date1, Date date2) {
		return (int) TimeUnit.DAYS.convert(date2.getTime() - date1.getTime(), TimeUnit.MILLISECONDS);

	}

	@Override
	public Borrower updateFine(String cardId) {
		Borrower borrower = borrowerRepository.findByCardId(cardId);
		List<Object> resultList = loanRepository.findByCardId(borrower);
		if (null == resultList || resultList.isEmpty()) {
			throw new ApplicationException("500", "Card Id not found");
		} else {
			for (Object resObj : resultList) {
				BookLoan loan = (BookLoan) resObj;
				Fines fines = fineRepository.findByLoanId(loan);
				if (loan.getDateIn() != null && fines.isPaid() == false) {
					fines.setPaid(true);
					fineRepository.save(fines);
				}

			}
		}
		return borrower;

	}
	public List<Map<String, String>> getFineDetails() {
		List<Object[]> resultList = this.loanRepository.getFineAmount();//groups the loan amt by card ids
		List<Map<String, String>> returnList = new ArrayList<Map<String, String>>();
		Map<String, String> map = null;
		if (null == resultList || resultList.isEmpty()) {
			throw new ApplicationException("500", "No fines found");
		} else {
			for (Object[] resObj : resultList) {
				Borrower borower = (Borrower) resObj[0];
				Double abs = Math.abs((Double)resObj[1]);
				String amountDue = String.valueOf(abs);
				map = new HashMap<String, String>();
				map.put("cardId", borower.getCardId());
				map.put("amountDue", amountDue);
				map.put("name", borower.getBname());
				returnList.add(map);

			}
		}
		return returnList;
	}
}
