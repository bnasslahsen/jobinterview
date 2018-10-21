package com.badrconsulting.jobinterview.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.badrconsulting.jobinterview.service.dto.DomainDTO;

/**
 * Service Interface for managing Domain.
 */
public interface DomainService {

	/**
	 * Save a domain.
	 * @param domainDTO the entity to save
	 * @return the persisted entity
	 */
	DomainDTO save(DomainDTO domainDTO);

	/**
	 * Get all the domains.
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	Page<DomainDTO> findAll(Pageable pageable);

	/**
	 * Get the "id" domain.
	 * @param id the id of the entity
	 * @return the entity
	 */
	DomainDTO findOne(Long id);

	/**
	 * Delete the "id" domain.
	 * @param id the id of the entity
	 */
	void delete(Long id);

	/**
	 * Search for the domain corresponding to the query.
	 * @param query the query of the search
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	Page<DomainDTO> search(String query, Pageable pageable);

}
