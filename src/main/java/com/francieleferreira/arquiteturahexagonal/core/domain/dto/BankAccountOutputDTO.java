package com.francieleferreira.arquiteturahexagonal.core.domain.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record BankAccountOutputDTO(
    String accountNumber,
    BigDecimal balanceAmount,
    LocalDateTime creationDate,
    LocalDateTime lastUpdateDate
){}
