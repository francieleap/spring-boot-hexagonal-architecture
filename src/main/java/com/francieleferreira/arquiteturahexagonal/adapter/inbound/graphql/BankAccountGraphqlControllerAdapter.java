package com.francieleferreira.arquiteturahexagonal.adapter.inbound.graphql;

import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountInputDTO;
import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountOutputDTO;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.BalanceAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.CreateAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.DepositAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.WithdrawAccountInputPort;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.math.BigDecimal;

@Controller
public class BankAccountGraphqlControllerAdapter {
    private final CreateAccountInputPort createAccountInputPort;
    private final DepositAccountInputPort depositAccountInputPort;
    private final WithdrawAccountInputPort withdrawAccountInputPort;
    private final BalanceAccountInputPort balanceAccountInputPort;

    public BankAccountGraphqlControllerAdapter(CreateAccountInputPort createAccountInputPort, DepositAccountInputPort depositAccountInputPort, WithdrawAccountInputPort withdrawAccountInputPort, BalanceAccountInputPort balanceAccountInputPort) {
        this.createAccountInputPort = createAccountInputPort;
        this.depositAccountInputPort = depositAccountInputPort;
        this.withdrawAccountInputPort = withdrawAccountInputPort;
        this.balanceAccountInputPort = balanceAccountInputPort;
    }

    @MutationMapping
    public BankAccountOutputDTO create(@Argument BankAccountInputDTO dto) {
        return createAccountInputPort.create(dto);
    }

    @QueryMapping
    public BigDecimal getBalance(@Argument String accountNumber) {
        return balanceAccountInputPort.getBalance(accountNumber);
    }

    @MutationMapping
    public boolean deposit(@Argument BankAccountInputDTO dto) {
        try {
            depositAccountInputPort.deposit(dto.accountNumber(), dto.balanceAmount());
            return true;
        } catch (Exception e){
            return false;
        }
    }

    @MutationMapping
    public boolean withdraw(@Argument BankAccountInputDTO dto) {
        try {
            withdrawAccountInputPort.withdraw(dto.accountNumber(), dto.balanceAmount());
            return true;
        } catch (Exception e){
            return false;
        }
    }
}
