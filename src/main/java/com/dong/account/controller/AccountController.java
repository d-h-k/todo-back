package com.dong.account.controller;

import com.dong.account.dto.AccountRequestDto;
import com.dong.account.dto.AccountResponseDto;
import com.dong.account.entity.Account;
import com.dong.account.service.AccountService;
import com.dong.common.ResponseWrapper;
import com.dong.common.auth.AuthUser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dong.common.ResponseWrapper.wrapOk;

@RequestMapping("api/v1/account")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping
    public ResponseEntity<?> creatAccount(@RequestBody AccountRequestDto request) {
        Account account = accountService.create(request.createAccount());

        new AccountResponseDto(account);

        return wrapOk(account)
                .jsonResponse();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> readAccount(@PathVariable Long id) {
        AccountResponseDto responseDto = new AccountResponseDto(accountService.read(id));

        return wrapOk(responseDto)
                .jsonResponse();
    }

    @GetMapping()
    public ResponseEntity<?> readAccounts(Pageable pageable, @AuthUser Account account) {
        Page<Account> accounts = accountService.readPage(pageable);
        ResponseWrapper<?> result = ResponseWrapper.wrapOk(AccountResponseDto.pages(accounts));
        log.info("Account 정보 : {}", account.toString());

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

}
