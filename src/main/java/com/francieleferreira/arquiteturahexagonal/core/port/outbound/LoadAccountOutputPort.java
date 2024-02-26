package com.francieleferreira.arquiteturahexagonal.core.port.outbound;

import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountOutputDTO;

import java.util.Optional;

public interface LoadAccountOutputPort {
    Optional<BankAccountOutputDTO> load(String accountNumber);
}