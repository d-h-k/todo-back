package com.dong.account.controller;

import com.dong.account.dto.AccountRequestDto;
import com.dong.account.dto.AccountResponseDto;
import com.dong.account.entity.Account;
import com.dong.account.service.AccountService;
import com.dong.common.auth.AuthUser;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.dong.account.dto.AccountResponseDto.pageResponse;
import static com.dong.common.ResponseListWrapper.listWrapOk;
import static com.dong.common.ResponseWrapper.wrapOk;

@RequestMapping("api/account/v1")
@RestController
@RequiredArgsConstructor
public class AccountController {

    private final AccountService accountService;

    private final Logger log = LoggerFactory.getLogger(getClass());

    @PostMapping
    public ResponseEntity<?> makeAccount(@RequestBody AccountRequestDto request) {
        AccountResponseDto responseDto = new AccountResponseDto(accountService.create(request.createAccount()));

        return wrapOk(responseDto)
                .jsonResponse();
    }


    @GetMapping("/{id}")
    public ResponseEntity<?> account(@PathVariable Long id) {
        AccountResponseDto responseDto = new AccountResponseDto(accountService.read(id));

        return wrapOk(responseDto)
                .jsonResponse();
    }

    @GetMapping()
    public ResponseEntity<?> AccountList(Pageable pageable, @AuthUser Account account) {
        Page<Account> accounts = accountService.readPage(pageable);
        Page<AccountResponseDto> responseDtos = pageResponse(accounts);


        return listWrapOk(responseDtos).jsonResponse();
    }

}
