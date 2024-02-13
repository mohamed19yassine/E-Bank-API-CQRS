package com.cqrs.demo.commands.aggregates;

import com.cqrs.demo.commonapi.commands.CreateAccountCommand;
import com.cqrs.demo.commonapi.commands.CreditAccountCommand;
import com.cqrs.demo.commonapi.commands.DebitAccountCommand;
import com.cqrs.demo.commonapi.enums.AccountStatus;
import com.cqrs.demo.commonapi.events.AccountCreatedEvent;
import com.cqrs.demo.commonapi.events.AccountCreditedEvent;
import com.cqrs.demo.commonapi.events.AccountDebitededEvent;
import com.cqrs.demo.commonapi.exception.NegativeInitialBalanceException;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;

@Aggregate
public class AccountAggregate {
    @AggregateIdentifier
    private String accountId;
    private String currency;
    private double balance;
    private AccountStatus status;

    public AccountAggregate(){
        //required by AXON
    }
    @CommandHandler
    public AccountAggregate(CreateAccountCommand command){
        if(command.getInitialBalance()<0)throw new NegativeInitialBalanceException("Negative balance");
        AggregateLifecycle.apply(new AccountCreatedEvent(
                command.getId(),
                command.getCurrency(),
                command.getInitialBalance(),
                AccountStatus.CREATED
        ));
    }
    @EventSourcingHandler
    public void on(AccountCreatedEvent event){
        this.accountId=event.getId();
        this.balance=event.getBalance();
        this.status=event.getStatus();
        this.currency=event.getCurrency();

    }

    @CommandHandler
    public void handle(CreditAccountCommand  command){
        if(command.getAmount() <0)throw new NegativeInitialBalanceException("Negative Amount");
        AggregateLifecycle.apply(new AccountCreditedEvent(
                command.getId(),
                command.getCurrency(),
                command.getAmount()
        ));

    }
    @EventSourcingHandler
    public void on(AccountCreditedEvent event){
        this.accountId=event.getId();
        this.balance+=event.getAmount();
        this.currency=event.getCurrency();


    }

    @CommandHandler
    public void handle(DebitAccountCommand   command){
        if(command.getAmount() <0)throw new NegativeInitialBalanceException("Negative Amount");
        if(command.getAmount() >this.balance)throw new NegativeInitialBalanceException("Balance insuffient Exception");
        AggregateLifecycle.apply(new AccountDebitededEvent(
                command.getId(),
                command.getCurrency(),
                command.getAmount()
        ));

    }
    @EventSourcingHandler
    public void on(AccountDebitededEvent event){
        this.balance-=event.getAmount();

    }
}
