package com.dong.account.dto;

import com.dong.account.entity.Account;
import com.dong.account.entity.AccountType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AccountRequestDto {
    private String nickname;
    private String accountType;

    public Account createAccount() {
        return Account.builder()
                .nickname(nickname)
                .accountType(AccountType.castFromString(accountType))
                .authentication("NONE")
                .quit(Boolean.FALSE)
                .build();
    }
}
