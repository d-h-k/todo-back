package com.dong.account.entity;

import com.dong.account.exception.NoSuchAccountException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum AccountType {

    ADMIN("Admin", "관리자"),
    LEADER("Leader", "팀 리더"),
    TUTOR("Tutor", "교수님"),
    ASSISTANT("Assistant", "조교"),
    NEWBIE("Newbie", "신입"),
    GUEST("Guest", "비회원");

    private static final Map<String, AccountType> typeMap = new HashMap<>();

    static {
        typeMap.put(ADMIN.getTypeKey(), ADMIN);
        typeMap.put(LEADER.getTypeKey(), LEADER);
        typeMap.put(TUTOR.getTypeKey(), TUTOR);
        typeMap.put(ASSISTANT.getTypeKey(), ASSISTANT);
        typeMap.put(NEWBIE.getTypeKey(), NEWBIE);
        typeMap.put(GUEST.getTypeKey(), GUEST);
    }

    private final String typeKey;
    private final String description;

    public static AccountType castFromString(String typeKey) {
        if (typeMap.containsKey(typeKey)) {
            return typeMap.get(typeKey);
        }
        throw new NoSuchAccountException();
    }
}
