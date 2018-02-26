package edu.utdallas.sxb170035.library_app.data.jpa.service;

import java.util.List;
import java.util.Map;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Book;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.BookLoan;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.Borrower;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.Fines;

interface FineRepository extends Repository<Fines, Long> {
	

	//List<Fines> saveAll(List<Fines> finesList);
	Fines findByLoanId(String loanId);
	Fines save(Fines fines);
}
