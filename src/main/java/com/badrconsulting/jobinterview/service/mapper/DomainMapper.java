package com.badrconsulting.jobinterview.service.mapper;

import org.mapstruct.Mapper;

import com.badrconsulting.jobinterview.model.Domain;
import com.badrconsulting.jobinterview.service.dto.DomainDTO;

/**
 * Mapper for the entity Domain and its DTO DomainDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface DomainMapper extends EntityMapper <DomainDTO, Domain> {
    
    
    default Domain fromId(Long id) {
        if (id == null) {
            return null;
        }
        Domain domain = new Domain();
        domain.setId(id);
        return domain;
    }
}
