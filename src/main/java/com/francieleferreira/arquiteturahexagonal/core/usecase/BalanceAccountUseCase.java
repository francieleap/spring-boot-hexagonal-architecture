package com.francieleferreira.arquiteturahexagonal.core.usecase;

import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountOutputDTO;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.BalanceAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.outbound.LoadAccountOutputPort;

import java.math.BigDecimal;
import java.util.Optional;

public class BalanceAccountUseCase implements BalanceAccountInputPort {

    private final LoadAccountOutputPort loadAccountOutputPort;

    public BalanceAccountUseCase(LoadAccountOutputPort loadAccountOutputPort) {
        this.loadAccountOutputPort = loadAccountOutputPort;
    }

    @Override
    public BigDecimal getBalance(String accountNumber) {

        Optional<BankAccountOutputDTO> account = loadAccountOutputPort.load(accountNumber);

        if (account.isPresent()) {
            return account.get().balanceAmount();

        }
        throw new RuntimeException("There is no account registered with the number informed!");
    }
}
