package com.francieleferreira.arquiteturahexagonal.core.port.inbound;

import java.math.BigDecimal;

public interface WithdrawAccountInputPort {

    void withdraw(String accountNumber, BigDecimal amount);
}
