package edu.utdallas.sxb170035.library_app.data.jpa.repository;

import org.springframework.data.repository.Repository;

import edu.utdallas.sxb170035.library_app.data.jpa.domain.Borrower;

public interface BorrowerRepository extends Repository<Borrower, Long> {

	Borrower findByCardId(String cardId);

	Borrower save(Borrower entity);

	Borrower findBySsn(String ssn);

}
