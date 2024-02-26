package com.francieleferreira.arquiteturahexagonal.core.usecase;

import com.francieleferreira.arquiteturahexagonal.core.domain.BankAccount;
import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountInputDTO;
import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountOutputDTO;
import com.francieleferreira.arquiteturahexagonal.core.domain.mapper.BankAccountMapper;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.CreateAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.outbound.LoadAccountOutputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.outbound.SaveAccountOutputPort;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

public class CreateAccountUseCase implements CreateAccountInputPort {

    private final LoadAccountOutputPort loadAccountOutputPort;
    private final SaveAccountOutputPort saveAccountOutputPort;

    public CreateAccountUseCase(LoadAccountOutputPort loadAccountOutputPort, SaveAccountOutputPort saveAccountOutputPort) {
        this.loadAccountOutputPort = loadAccountOutputPort;
        this.saveAccountOutputPort = saveAccountOutputPort;
    }

    @Override
    public BankAccountOutputDTO create(BankAccountInputDTO bankAccountInputDTO) {

        BankAccount bankAccount = BankAccountMapper.INSTANCE.toDomain(bankAccountInputDTO);

        Optional<BankAccountOutputDTO> account = loadAccountOutputPort.load(bankAccountInputDTO.accountNumber());

        if (account.isPresent()) {
            throw new RuntimeException("There is already an account registered with the number informed!");
        } else {
            bankAccount.setBalanceAmount(BigDecimal.ZERO);
            bankAccount.setCreationDate(LocalDateTime.now());

            return saveAccountOutputPort.save(BankAccountMapper.INSTANCE.toOutput(bankAccount));
        }
    }
}
