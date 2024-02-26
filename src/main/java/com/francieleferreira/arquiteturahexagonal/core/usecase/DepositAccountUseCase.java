package com.francieleferreira.arquiteturahexagonal.core.usecase;

import com.francieleferreira.arquiteturahexagonal.core.domain.BankAccount;
import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountOutputDTO;
import com.francieleferreira.arquiteturahexagonal.core.domain.mapper.BankAccountMapper;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.DepositAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.outbound.LoadAccountOutputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.outbound.SaveAccountOutputPort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;

public class DepositAccountUseCase implements DepositAccountInputPort {

    private final LoadAccountOutputPort loadAccountOutputPort;
    private final SaveAccountOutputPort saveAccountOutputPort;

    public DepositAccountUseCase(LoadAccountOutputPort loadAccountOutputPort, SaveAccountOutputPort saveAccountOutputPort) {
        this.loadAccountOutputPort = loadAccountOutputPort;
        this.saveAccountOutputPort = saveAccountOutputPort;
    }

    @Override
    public void deposit(String accountNumber, BigDecimal amount) {

        BankAccountOutputDTO account = loadAccountOutputPort.load(accountNumber)
                .orElseThrow(NoSuchElementException::new);

        BankAccount bankAccount = BankAccountMapper.INSTANCE.toDomain(account);

        bankAccount.deposit(amount);

        bankAccount.setLastUpdateDate(LocalDateTime.now());

        saveAccountOutputPort.save(BankAccountMapper.INSTANCE.toOutput(bankAccount));

    }
}
