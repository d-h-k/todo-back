package com.dong.account.service;

import com.dong.account.dto.AccountResponseDto;
import com.dong.account.entity.Account;
import com.dong.account.entity.AccountType;
import com.dong.account.exception.NoSuchAccountException;
import com.dong.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

import static com.dong.common.auth.UnknownAccount.guestAccount;

@Service
@RequiredArgsConstructor
public class AccountService {

    private final AccountRepository accountRepository;

    Logger log = LoggerFactory.getLogger(getClass());

    @PostConstruct
    public void registry() {

        Account account = accountRepository.save(guestAccount());
        log.info("registry GUEST: {}", new AccountResponseDto(account));
    }

    public Account getAccount(Long accountId, String accountTypeString) {

        AccountType accountTypeEnum = AccountType.castFromString(accountTypeString);
        return accountRepository
                .findByIdAndAccountType(accountId, accountTypeEnum)
                .orElseThrow(NoSuchAccountException::new);
    }

    public Account create(Account account) {

        return accountRepository.save(account);
    }

    public Account read(Long id) {

        return accountRepository
                .findById(id)
                .orElseThrow(NoSuchAccountException::new);
    }


    public Page<Account> readPage(Pageable pageable) {

        return accountRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Account authInfoParser(String authInfo) {
        //todo 리팩토링 필요, 테스트 필요
        try {
            String[] authStrings = authInfo.split(" ");
            Long accountId = Long.parseLong(authStrings[1]);
            String accountType = authStrings[0];
            return this.getAccount(accountId, accountType);
        } catch (NoSuchAccountException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            log.info("잘못된 회원 인증헤더 정보 {}", authInfo);
        }
        return guestAccount();
    }
}
