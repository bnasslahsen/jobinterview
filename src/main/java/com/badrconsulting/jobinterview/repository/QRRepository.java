package com.badrconsulting.jobinterview.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.badrconsulting.jobinterview.model.QR;

/**
 * Spring Data JPA repository for the QR entity.
 */
@Repository
public interface QRRepository extends JpaRepository<QR, Long> {

	@Query("SELECT qr FROM QR qr inner join qr.domain as domain where domain.name = ?1 and domain.language = ?2")
	Page<QR> searchByDomaineNameForLanguage(String domaineName, String language, Pageable pageable);

	@Query("select qr.id from QR qr")
	List<Long> getAllIds();
	
	
}
