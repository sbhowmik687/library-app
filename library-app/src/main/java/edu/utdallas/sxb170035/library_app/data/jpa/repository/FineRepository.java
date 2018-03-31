package edu.utdallas.sxb170035.library_app.data.jpa.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.BookLoan;
import edu.utdallas.sxb170035.library_app.data.jpa.domain.Fines;

public interface FineRepository extends PagingAndSortingRepository<Fines, Long> {
	

	List<Fines> save(List<Fines> finesList);
	Fines save(Fines fines);
	Fines findByLoanId(BookLoan bookLoan);
}
