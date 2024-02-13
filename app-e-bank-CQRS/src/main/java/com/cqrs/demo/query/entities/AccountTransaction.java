package com.cqrs.demo.query.entities;


import com.cqrs.demo.commonapi.enums.TransactionType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AccountTransaction implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private Instant timestamp;
    private double amount;
    @Enumerated(EnumType.STRING)
    private TransactionType type;

    @ManyToOne
    private Account account;
}
