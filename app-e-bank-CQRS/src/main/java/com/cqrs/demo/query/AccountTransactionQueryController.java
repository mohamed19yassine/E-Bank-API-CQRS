package com.cqrs.demo.query;

import com.cqrs.demo.query.entities.Account;
import com.cqrs.demo.query.entities.AccountTransaction;
import com.cqrs.demo.query.queries.GetAccountById;
import com.cqrs.demo.query.queries.GetAllAccountTransactions;
import com.cqrs.demo.query.queries.GetAllAccountTransactionsByIdAccount;
import com.cqrs.demo.query.queries.GetAllAccounts;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping(path="/query/accountTransaction")
public class AccountTransactionQueryController {
    private QueryGateway queryGateway;

    public AccountTransactionQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }
    @GetMapping("/list")
    public CompletableFuture<List<AccountTransaction>> getAccountTransactionList(){
        return queryGateway.query(new GetAllAccountTransactions(), ResponseTypes.multipleInstancesOf(AccountTransaction.class));
    }
    @GetMapping("/{idAccount}")
    public CompletableFuture<List<AccountTransaction>> getAccountTransactionByIdAccount(@PathVariable String idAccount){
        return queryGateway.query(new GetAllAccountTransactionsByIdAccount(idAccount), ResponseTypes.multipleInstancesOf(AccountTransaction.class));
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exeptionHandler(Exception exception){
        return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
