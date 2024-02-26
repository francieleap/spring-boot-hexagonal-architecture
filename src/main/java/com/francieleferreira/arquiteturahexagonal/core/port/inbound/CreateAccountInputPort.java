package com.francieleferreira.arquiteturahexagonal.core.port.inbound;

import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountInputDTO;
import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountOutputDTO;

public interface CreateAccountInputPort {

    BankAccountOutputDTO create(BankAccountInputDTO bankAccount);
}
