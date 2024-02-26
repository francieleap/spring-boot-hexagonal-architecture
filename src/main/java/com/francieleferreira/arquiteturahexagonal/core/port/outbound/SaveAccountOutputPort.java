package com.francieleferreira.arquiteturahexagonal.core.port.outbound;

import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountOutputDTO;

public interface SaveAccountOutputPort {
    BankAccountOutputDTO save(BankAccountOutputDTO bankAccount);
}