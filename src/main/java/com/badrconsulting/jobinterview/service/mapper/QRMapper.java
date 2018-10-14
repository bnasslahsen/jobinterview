package com.badrconsulting.jobinterview.service.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.badrconsulting.jobinterview.model.QR;
import com.badrconsulting.jobinterview.service.dto.QRDTO;

/**
 * Mapper for the entity QR and its DTO QRDTO.
 */
@Mapper(componentModel = "spring", uses = {DomainMapper.class, })
public interface QRMapper extends EntityMapper <QRDTO, QR> {

    @Mapping(source = "domain.id", target = "domainId")
    QRDTO toDto(QR qR); 

    @Mapping(source = "domainId", target = "domain")
    QR toEntity(QRDTO qRDTO); 
    default QR fromId(Long id) {
        if (id == null) {
            return null;
        }
        QR qR = new QR();
        qR.setId(id);
        return qR;
    }
}
