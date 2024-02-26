package com.francieleferreira.arquiteturahexagonal.adapter.outbound;

import com.francieleferreira.arquiteturahexagonal.adapter.outbound.entity.BankAccountEntity;
import com.francieleferreira.arquiteturahexagonal.adapter.outbound.mapper.BankAccountMapper;
import com.francieleferreira.arquiteturahexagonal.adapter.outbound.repository.BankAccountRepository;
import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountOutputDTO;
import com.francieleferreira.arquiteturahexagonal.core.port.outbound.LoadAccountOutputPort;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class LoadAccountOutputAdapter implements LoadAccountOutputPort {

    private final BankAccountRepository repository;

    public LoadAccountOutputAdapter(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    public Optional<BankAccountOutputDTO> load(String accountNumber) {

        BankAccountEntity bankAccountEntity = repository.findByAccountNumber(accountNumber).orElse(null);

        return Optional.ofNullable(BankAccountMapper.INSTANCE.toDomain(bankAccountEntity));
    }
}
