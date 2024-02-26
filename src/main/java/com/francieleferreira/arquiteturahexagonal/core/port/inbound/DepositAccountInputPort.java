package com.francieleferreira.arquiteturahexagonal.core.port.inbound;

import java.math.BigDecimal;

public interface DepositAccountInputPort {

    void deposit(String accountNumber, BigDecimal amount);
}
