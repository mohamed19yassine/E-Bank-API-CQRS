package com.cqrs.demo.query.services;

import com.cqrs.demo.commonapi.enums.TransactionType;
import com.cqrs.demo.commonapi.events.AccountCreatedEvent;
import com.cqrs.demo.commonapi.events.AccountCreditedEvent;
import com.cqrs.demo.commonapi.events.AccountDebitededEvent;
import com.cqrs.demo.query.entities.Account;
import com.cqrs.demo.query.entities.AccountTransaction;
import com.cqrs.demo.query.queries.GetAccountById;
import com.cqrs.demo.query.queries.GetAllAccountTransactions;
import com.cqrs.demo.query.queries.GetAllAccountTransactionsByIdAccount;
import com.cqrs.demo.query.queries.GetAllAccounts;
import com.cqrs.demo.query.repository.AccountRepository;
import com.cqrs.demo.query.repository.AccountTransactionRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.eventhandling.EventMessage;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.UUID;

@Service
@Slf4j
@Transactional
@AllArgsConstructor
public class AccountEventHandlerService {

    private AccountTransactionRepository accountTransactionRepository;
    private AccountRepository accountRepository;
    @EventHandler
    public void on(AccountCreatedEvent  event, EventMessage<AccountCreatedEvent>eventMessage){


        log.info("*********************");
        log.info("AccountRepository received");

        Account account=new Account();
        account.setId(event.getId());
        account.setBalance(event.getBalance());
        account.setStatus(event.getStatus());
        account.setCurrency(event.getCurrency());
        account.setCreatedAt(eventMessage.getTimestamp());

        accountRepository.save(account);
    }

    @EventHandler
    public void on(AccountCreditedEvent event,EventMessage<AccountCreditedEvent>creditedEventEventMessage){
        AccountTransaction accountTransaction= new AccountTransaction();
        accountTransaction.setTimestamp(creditedEventEventMessage.getTimestamp());
        accountTransaction.setAmount(event.getAmount());
        accountTransaction.setType(TransactionType.CREDIT);

        Account account = accountRepository.findById(event.getId())
                .orElseThrow(() -> new NoSuchElementException("Aucun compte trouvé avec l'identifiant spécifié"));
        accountTransaction.setAccount(account);
        accountTransactionRepository.save(accountTransaction);
    }


    @EventHandler
    public void onDebiteded(AccountDebitededEvent event ,EventMessage<AccountDebitededEvent>debitededEventEventMessage) {
        AccountTransaction accountTransaction= new AccountTransaction();
        accountTransaction.setTimestamp(debitededEventEventMessage.getTimestamp());
        accountTransaction.setAmount(event.getAmount());
        accountTransaction.setType(TransactionType.DEBIT);
        Account account = accountRepository.findById(event.getId())
                .orElseThrow(() -> new NoSuchElementException("Aucun compte trouvé avec l'identifiant spécifié"));
        accountTransaction.setAccount(account);
        accountTransactionRepository.save(accountTransaction);
    }



    @QueryHandler
    public List<Account> on(GetAllAccounts query){
        return accountRepository.findAll();
    }
    @QueryHandler
    public Account on(GetAccountById query){
        return accountRepository.findById(query.getId()).get();
    }



    @QueryHandler
    public List<AccountTransaction> on(GetAllAccountTransactions query){

        return accountTransactionRepository.findAll();

    }
    @QueryHandler
    public List<AccountTransaction> on(GetAllAccountTransactionsByIdAccount query){

        return accountTransactionRepository. findByAccountId(query.getId());


    }




}
