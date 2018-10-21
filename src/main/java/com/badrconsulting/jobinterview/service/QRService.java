package com.badrconsulting.jobinterview.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.badrconsulting.jobinterview.service.dto.QRDTO;

/**
 * Service Interface for managing QR.
 */
public interface QRService {

	/**
	 * Save a qR.
	 * @param qRDTO the entity to save
	 * @return the persisted entity
	 */
	QRDTO save(QRDTO qRDTO);

	/**
	 * Get all the qRS.
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	Page<QRDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" qR.
	 * @param id the id of the entity
	 * @return the entity
	 */
	QRDTO findOne(Long id);

	/**
	 * Delete the "id" qR.
	 * @param id the id of the entity
	 */
	void delete(Long id);

	/**
	 * Search for the qR corresponding to the query.
	 * @param query the query of the search
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	Page<QRDTO> search(String query, Pageable pageable);

	List<Long> getAllIds();

	Page<QRDTO> searchByDomaineNameForLanguage(String domaineName, String language,
			Pageable pageable);

}
