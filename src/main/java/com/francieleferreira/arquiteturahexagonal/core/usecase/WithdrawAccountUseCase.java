package com.francieleferreira.arquiteturahexagonal.core.usecase;

import com.francieleferreira.arquiteturahexagonal.core.domain.BankAccount;
import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountOutputDTO;
import com.francieleferreira.arquiteturahexagonal.core.domain.mapper.BankAccountMapper;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.WithdrawAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.outbound.LoadAccountOutputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.outbound.SaveAccountOutputPort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class WithdrawAccountUseCase implements WithdrawAccountInputPort {

    private final LoadAccountOutputPort loadAccountOutputPort;
    private final SaveAccountOutputPort saveAccountOutputPort;

    public WithdrawAccountUseCase(LoadAccountOutputPort loadAccountOutputPort, SaveAccountOutputPort saveAccountOutputPort) {
        this.loadAccountOutputPort = loadAccountOutputPort;
        this.saveAccountOutputPort = saveAccountOutputPort;
    }

    @Override
    public void withdraw(String accountNumber, BigDecimal amount) {

        BankAccountOutputDTO account = loadAccountOutputPort.load(accountNumber)
                .orElseThrow(NoSuchElementException::new);

        BankAccount bankAccount = BankAccountMapper.INSTANCE.toDomain(account);
        boolean hasWithdrawn = bankAccount.withdraw(amount);

        if (!hasWithdrawn) {
            throw new RuntimeException("There is not enough balance to withdraw the amount informed!");
        }

        bankAccount.setLastUpdateDate(LocalDateTime.now());

        saveAccountOutputPort.save(BankAccountMapper.INSTANCE.toOutput(bankAccount));
    }
}
