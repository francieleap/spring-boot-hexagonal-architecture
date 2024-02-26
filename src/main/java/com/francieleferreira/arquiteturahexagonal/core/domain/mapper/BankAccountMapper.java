package com.francieleferreira.arquiteturahexagonal.core.domain.mapper;

import com.francieleferreira.arquiteturahexagonal.core.domain.BankAccount;
import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountInputDTO;
import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountOutputDTO;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BankAccountMapper {

    BankAccountMapper INSTANCE = Mappers.getMapper(BankAccountMapper.class);

    BankAccount toDomain(BankAccountInputDTO request);
    BankAccountInputDTO toInput(BankAccount domain);

    BankAccount toDomain(BankAccountOutputDTO response);
    BankAccountOutputDTO toOutput(BankAccount domain);
}
