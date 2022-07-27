package com.dong.account.repository;


import com.dong.account.entity.Account;
import com.dong.account.entity.AccountType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findByIdAndAccountType(Long accountId, AccountType accountType);
}
