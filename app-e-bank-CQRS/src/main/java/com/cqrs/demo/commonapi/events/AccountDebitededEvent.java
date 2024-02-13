package com.cqrs.demo.commonapi.events;

import com.cqrs.demo.commonapi.enums.AccountStatus;
import lombok.Getter;

public class AccountDebitededEvent extends BaseEvent<String>{


    @Getter
    private String currency;
    @Getter
    private double amount;
    public AccountDebitededEvent(String id, String currency, double amount) {
        super(id);
        this.currency = currency;
        this.amount = amount;
    }
}
