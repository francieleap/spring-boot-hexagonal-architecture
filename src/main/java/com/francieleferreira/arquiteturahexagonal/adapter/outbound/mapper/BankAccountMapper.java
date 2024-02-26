package com.francieleferreira.arquiteturahexagonal.adapter.outbound.mapper;

import com.francieleferreira.arquiteturahexagonal.adapter.outbound.entity.BankAccountEntity;
import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankAccountMapper {

    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);
    BankAccountOutputDTO toDomain(BankAccountEntity entity);
    BankAccountEntity toEntity(BankAccountOutputDTO domain);
}

