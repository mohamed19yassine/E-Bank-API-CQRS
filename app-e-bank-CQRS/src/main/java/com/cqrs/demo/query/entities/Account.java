package com.cqrs.demo.query.entities;


import com.cqrs.demo.commonapi.enums.AccountStatus;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.*;

import java.time.Instant;
import java.util.List;
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Account {
    @Id
    private String id;
    private Instant createdAt;
    private double balance;
    private AccountStatus status;
    private String currency;
    @OneToMany(mappedBy = "account")
    @ToString.Exclude // casser la boucle toString
    @JsonIgnore // casser la boucle JSON
    private List<AccountTransaction> transactions;


}
