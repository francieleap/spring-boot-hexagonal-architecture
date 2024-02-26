package com.francieleferreira.arquiteturahexagonal.core.port.inbound;

import java.math.BigDecimal;

public interface BalanceAccountInputPort {

    BigDecimal getBalance(String accountNumber);
}
