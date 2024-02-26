package com.francieleferreira.arquiteturahexagonal.core.domain;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class BankAccount {

    private String accountNumber;
    private BigDecimal balanceAmount;

    private LocalDateTime creationDate;

    private LocalDateTime lastUpdateDate;

    public boolean withdraw(BigDecimal amount) {
        if(balanceAmount.compareTo(amount) < 0) {
            return false;
        }

        balanceAmount = balanceAmount.subtract(amount);
        return true;
    }

    public void deposit(BigDecimal amount) {
        balanceAmount = balanceAmount.add(amount);
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public BigDecimal getBalanceAmount() {
        return balanceAmount;
    }

    public void setBalanceAmount(BigDecimal balanceAmount) {
        this.balanceAmount = balanceAmount;
    }

    public LocalDateTime getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(LocalDateTime creationDate) {
        this.creationDate = creationDate;
    }

    public LocalDateTime getLastUpdateDate() {
        return lastUpdateDate;
    }

    public void setLastUpdateDate(LocalDateTime lastUpdateDate) {
        this.lastUpdateDate = lastUpdateDate;
    }
}
