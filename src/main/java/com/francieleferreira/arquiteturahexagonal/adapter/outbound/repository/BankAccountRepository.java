package com.francieleferreira.arquiteturahexagonal.adapter.outbound.repository;

import com.francieleferreira.arquiteturahexagonal.adapter.outbound.entity.BankAccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface BankAccountRepository extends JpaRepository<BankAccountEntity, Long> {

    Optional<BankAccountEntity> findByAccountNumber(String accountNumber);
}
