package com.badrconsulting.jobinterview.controller;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.badrconsulting.jobinterview.controller.util.HeaderUtil;
import com.badrconsulting.jobinterview.controller.util.PaginationUtil;
import com.badrconsulting.jobinterview.service.DomainService;
import com.badrconsulting.jobinterview.service.dto.DomainDTO;

import io.micrometer.core.annotation.Timed;

/**
 * REST controller for managing Domain.
 */
@RestController
@RequestMapping("/api")
public class DomainResource {

	private final Logger log = LoggerFactory.getLogger(DomainResource.class);

	private static final String ENTITY_NAME = "domain";

	private final DomainService domainService;

	public DomainResource(DomainService domainService) {
		this.domainService = domainService;
	}

	/**
	 * POST /domains : Create a new domain.
	 * @param domainDTO the domainDTO to create
	 * @return the ResponseEntity with status 201 (Created) and with body the new
	 * domainDTO, or with status 400 (Bad Request) if the domain has already an ID
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PostMapping("/domains")
	@Timed
	public ResponseEntity<DomainDTO> createDomain(@Valid @RequestBody DomainDTO domainDTO)
			throws URISyntaxException {
		log.debug("REST request to save Domain : {}", domainDTO);
		if (domainDTO.getId() != null) {
			return ResponseEntity
					.badRequest().headers(HeaderUtil.createFailureAlert(ENTITY_NAME,
							"idexists", "A new domain cannot already have an ID"))
					.body(null);
		}
		DomainDTO result = domainService.save(domainDTO);
		return ResponseEntity.created(new URI("/api/domains/" + result.getId()))
				.headers(HeaderUtil.createEntityCreationAlert(ENTITY_NAME,
						result.getId().toString()))
				.body(result);
	}

	/**
	 * PUT /domains : Updates an existing domain.
	 * @param domainDTO the domainDTO to update
	 * @return the ResponseEntity with status 200 (OK) and with body the updated
	 * domainDTO, or with status 400 (Bad Request) if the domainDTO is not valid, or with
	 * status 500 (Internal Server Error) if the domainDTO couldnt be updated
	 * @throws URISyntaxException if the Location URI syntax is incorrect
	 */
	@PutMapping("/domains")
	@Timed
	public ResponseEntity<DomainDTO> updateDomain(@Valid @RequestBody DomainDTO domainDTO)
			throws URISyntaxException {
		log.debug("REST request to update Domain : {}", domainDTO);
		if (domainDTO.getId() == null) {
			return createDomain(domainDTO);
		}
		DomainDTO result = domainService.save(domainDTO);
		return ResponseEntity.ok().headers(HeaderUtil.createEntityUpdateAlert(ENTITY_NAME,
				domainDTO.getId().toString())).body(result);
	}

	/**
	 * GET /domains : get all the domains.
	 * @param pageable the pagination information
	 * @return the ResponseEntity with status 200 (OK) and the list of domains in body
	 */
	@GetMapping("/domains")
	@Timed
	public ResponseEntity<List<DomainDTO>> getAllDomains(Pageable pageable) {
		log.debug("REST request to get a page of Domains");
		Page<DomainDTO> page = domainService.findAll(pageable);
		HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(page,
				"/api/domains");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

	/**
	 * GET /domains/:id : get the "id" domain.
	 * @param id the id of the domainDTO to retrieve
	 * @return the ResponseEntity with status 200 (OK) and with body the domainDTO, or
	 * with status 404 (Not Found)
	 */
	@GetMapping("/domains/{id}")
	@Timed
	public ResponseEntity<DomainDTO> getDomain(@PathVariable Long id) {
		log.debug("REST request to get Domain : {}", id);
		DomainDTO domainDTO = domainService.findOne(id);
		return new ResponseEntity<>(domainDTO, HttpStatus.OK);
	}

	/**
	 * DELETE /domains/:id : delete the "id" domain.
	 * @param id the id of the domainDTO to delete
	 * @return the ResponseEntity with status 200 (OK)
	 */
	@DeleteMapping("/domains/{id}")
	@Timed
	public ResponseEntity<Void> deleteDomain(@PathVariable Long id) {
		log.debug("REST request to delete Domain : {}", id);
		domainService.delete(id);
		return ResponseEntity.ok()
				.headers(HeaderUtil.createEntityDeletionAlert(ENTITY_NAME, id.toString()))
				.build();
	}

	/**
	 * SEARCH /_search/domains?query=:query : search for the domain corresponding to the
	 * query.
	 * @param query the query of the domain search
	 * @param pageable the pagination information
	 * @return the result of the search
	 */
	@GetMapping("/_search/domains")
	@Timed
	public ResponseEntity<List<DomainDTO>> searchDomains(@RequestParam String query,
			Pageable pageable) {
		log.debug("REST request to search for a page of Domains for query {}", query);
		Page<DomainDTO> page = domainService.search(query, pageable);
		HttpHeaders headers = PaginationUtil.generateSearchPaginationHttpHeaders(query,
				page, "/api/_search/domains");
		return new ResponseEntity<>(page.getContent(), headers, HttpStatus.OK);
	}

}
