package com.dong.common.auth;

import com.dong.account.entity.Account;
import com.dong.account.entity.AccountType;

public final class UnknownAccount {
    private static final Account GUEST_ACCOUNT = Account.builder()
            .nickname("비회원")
            .accountType(AccountType.GUEST)
            .quit(Boolean.TRUE)
            .build();

    private UnknownAccount() {
    }

    public static Account guestAccount() {
        return GUEST_ACCOUNT;
    }
}
