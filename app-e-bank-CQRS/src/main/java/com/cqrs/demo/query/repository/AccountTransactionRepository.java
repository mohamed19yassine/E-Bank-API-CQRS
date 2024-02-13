package com.cqrs.demo.query.repository;

import com.cqrs.demo.query.entities.AccountTransaction;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountTransactionRepository extends JpaRepository<AccountTransaction,Long> {
    List<AccountTransaction> findByAccountId(String id);
}
