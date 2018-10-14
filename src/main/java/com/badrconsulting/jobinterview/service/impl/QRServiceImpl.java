package com.badrconsulting.jobinterview.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.badrconsulting.jobinterview.model.QR;
import com.badrconsulting.jobinterview.repository.QRRepository;
import com.badrconsulting.jobinterview.service.QRService;
import com.badrconsulting.jobinterview.service.dto.QRDTO;
import com.badrconsulting.jobinterview.service.mapper.QRMapper;

/**
 * Service Implementation for managing QR.
 */
@Service
@Transactional
public class QRServiceImpl implements QRService {

	private final Logger log = LoggerFactory.getLogger(QRServiceImpl.class);

	private final QRRepository qRRepository;

	private final QRMapper qRMapper;

	public QRServiceImpl(QRRepository qRRepository, QRMapper qRMapper) {
		this.qRRepository = qRRepository;
		this.qRMapper = qRMapper;
	}

	/**
	 * Save a qR.
	 *
	 * @param qRDTO the entity to save
	 * @return the persisted entity
	 */
	@Override
	public QRDTO save(QRDTO qRDTO) {
		log.debug("Request to save QR : {}", qRDTO);
		QR qR = qRMapper.toEntity(qRDTO);
		qR = qRRepository.save(qR);
		QRDTO result = qRMapper.toDto(qR);
		return result;
	}

	/**
	 * Get all the qRS.
	 *
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<QRDTO> findAll(Pageable pageable) {
		log.debug("Request to get all QRS");
		return qRRepository.findAll(pageable).map(qRMapper::toDto);
	}

	/**
	 * Get one qR by id.
	 *
	 * @param id the id of the entity
	 * @return the entity
	 */
	@Override
	@Transactional(readOnly = true)
	public QRDTO findOne(Long id) {
		log.debug("Request to get QR : {}", id);
		QR qR = qRRepository.getOne(id);
		return qRMapper.toDto(qR);
	}

	/**
	 * Delete the qR by id.
	 *
	 * @param id the id of the entity
	 */
	@Override
	public void delete(Long id) {
		log.debug("Request to delete QR : {}", id);
		qRRepository.deleteById(id);
	}

	/**
	 * Search for the qR corresponding to the query.
	 *
	 * @param query    the query of the search
	 * @param pageable the pagination information
	 * @return the list of entities
	 */
	@Override
	@Transactional(readOnly = true)
	public Page<QRDTO> search(String query, Pageable pageable) {
		log.debug("Request to search for a page of QRS for query {}", query);
		// TODO
		Page<QR> result = null;
		// Page<QR> result = qRSearchRepository.search(queryStringQuery(query),
		// pageable);
		return result.map(qRMapper::toDto);
	}

	@Override
	public List<Long> getAllIds() {
		return qRRepository.getAllIds();
	}

	@Override
	public Page<QRDTO> searchByDomaineNameForLanguage(String domaineName, String language, Pageable pageable) {
		Page<QR> qrs = qRRepository.searchByDomaineNameForLanguage(domaineName, language, pageable);
		return qrs.map(qRMapper::toDto);
	}
}
