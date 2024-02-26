package com.francieleferreira.arquiteturahexagonal.adapter.config;

import com.francieleferreira.arquiteturahexagonal.HexagonalArchitectureApplication;
import com.francieleferreira.arquiteturahexagonal.adapter.outbound.LoadAccountOutputAdapter;
import com.francieleferreira.arquiteturahexagonal.adapter.outbound.SaveAccountOutputAdapter;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.BalanceAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.CreateAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.DepositAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.port.inbound.WithdrawAccountInputPort;
import com.francieleferreira.arquiteturahexagonal.core.usecase.BalanceAccountUseCase;
import com.francieleferreira.arquiteturahexagonal.core.usecase.CreateAccountUseCase;
import com.francieleferreira.arquiteturahexagonal.core.usecase.DepositAccountUseCase;
import com.francieleferreira.arquiteturahexagonal.core.usecase.WithdrawAccountUseCase;
import graphql.scalars.ExtendedScalars;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

@Configuration
@ComponentScan(basePackageClasses = HexagonalArchitectureApplication.class)
public class BeanConfiguration {

    @Bean
    public CreateAccountInputPort createAccountUseCasePort(LoadAccountOutputAdapter loadAccountOutputAdapter, SaveAccountOutputAdapter saveAccountOutputAdapter) {
        return new CreateAccountUseCase(loadAccountOutputAdapter, saveAccountOutputAdapter);
    }

    @Bean
    public DepositAccountInputPort depositAccountUseCasePort(LoadAccountOutputAdapter loadAccountOutputAdapter, SaveAccountOutputAdapter saveAccountOutputAdapter) {
        return new DepositAccountUseCase(loadAccountOutputAdapter, saveAccountOutputAdapter);
    }

    @Bean
    public WithdrawAccountInputPort withdrawAccountUseCasePort(LoadAccountOutputAdapter loadAccountOutputAdapter, SaveAccountOutputAdapter saveAccountOutputAdapter) {
        return new WithdrawAccountUseCase(loadAccountOutputAdapter, saveAccountOutputAdapter);
    }

    @Bean
    public BalanceAccountInputPort balanceAccountInputPort(LoadAccountOutputAdapter loadAccountOutputAdapter) {
        return new BalanceAccountUseCase(loadAccountOutputAdapter);
    }

    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder
                .scalar(ExtendedScalars.DateTime);

    }

}
