package com.francieleferreira.arquiteturahexagonal.adapter.outbound;

import com.francieleferreira.arquiteturahexagonal.adapter.outbound.entity.BankAccountEntity;
import com.francieleferreira.arquiteturahexagonal.adapter.outbound.mapper.BankAccountMapper;
import com.francieleferreira.arquiteturahexagonal.adapter.outbound.repository.BankAccountRepository;
import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountOutputDTO;
import com.francieleferreira.arquiteturahexagonal.core.port.outbound.SaveAccountOutputPort;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;

import java.util.Optional;

@Component
public class SaveAccountOutputAdapter implements SaveAccountOutputPort {

    private final BankAccountRepository repository;

    public SaveAccountOutputAdapter(BankAccountRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional
    public BankAccountOutputDTO save(BankAccountOutputDTO bankAccount) {

        Optional<BankAccountEntity> bankAccountEntity = repository.findByAccountNumber(bankAccount.accountNumber());

        if (bankAccountEntity.isPresent()) {
            var bankAccountEntityUpdate = bankAccountEntity.get();
            BeanUtils.copyProperties(bankAccount, bankAccountEntityUpdate);
            return BankAccountMapper.INSTANCE.toDomain(repository.save(bankAccountEntityUpdate));
        } else {
            return BankAccountMapper.INSTANCE.toDomain(repository.save(BankAccountMapper.INSTANCE.toEntity(bankAccount)));
        }
    }
}
