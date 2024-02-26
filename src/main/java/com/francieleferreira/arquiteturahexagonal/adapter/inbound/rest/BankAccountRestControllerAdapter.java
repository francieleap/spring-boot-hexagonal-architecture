package com.francieleferreira.arquiteturahexagonal.adapter.inbound.rest;

import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountInputDTO;
import com.francieleferreira.arquiteturahexagonal.core.domain.dto.BankAccountOutputDTO;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.BalanceAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.CreateAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.DepositAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.WithdrawAccountInputPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
@RequestMapping("/accounts")
public class BankAccountRestControllerAdapter {

    private final CreateAccountInputPort createAccountInputPort;
    private final DepositAccountInputPort depositAccountInputPort;
    private final WithdrawAccountInputPort withdrawAccountInputPort;
    private final BalanceAccountInputPort balanceAccountInputPort;

    public BankAccountRestControllerAdapter(CreateAccountInputPort createAccountInputPort, DepositAccountInputPort depositAccountInputPort, WithdrawAccountInputPort withdrawAccountInputPort, BalanceAccountInputPort balanceAccountInputPort) {
        this.createAccountInputPort = createAccountInputPort;
        this.depositAccountInputPort = depositAccountInputPort;
        this.withdrawAccountInputPort = withdrawAccountInputPort;
        this.balanceAccountInputPort = balanceAccountInputPort;
    }


    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<BankAccountOutputDTO> create(@RequestBody BankAccountInputDTO dto){

        return new ResponseEntity<>(createAccountInputPort.create(dto), HttpStatus.CREATED);
    }

    @GetMapping("/{accountNumber}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<BigDecimal> getBalance(@PathVariable(value = "accountNumber") String accountNumber){

        return new ResponseEntity<>(balanceAccountInputPort.getBalance(accountNumber), HttpStatus.OK);
    }

    @PostMapping("/{accountNumber}/deposit")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deposit(@PathVariable(value = "accountNumber") String accountNumber, @RequestBody BigDecimal balanceAmount){
        depositAccountInputPort.deposit(accountNumber, balanceAmount);
    }

    @PostMapping("/{accountNumber}/withdraw")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void withdraw(@PathVariable(value = "accountNumber") String accountNumber, @RequestBody BigDecimal balanceAmount){
        withdrawAccountInputPort.withdraw(accountNumber, balanceAmount);
    }
}
