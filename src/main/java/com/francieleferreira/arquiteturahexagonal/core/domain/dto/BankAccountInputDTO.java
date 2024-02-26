package com.francieleferreira.arquiteturahexagonal.core.domain.dto;

import java.math.BigDecimal;

public record BankAccountInputDTO(
    String accountNumber,
    BigDecimal balanceAmount
){}
