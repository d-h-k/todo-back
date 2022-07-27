package com.dong.account.entity;

import com.community.exception.exceptions.NoSuchAccountTypeException;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

@AllArgsConstructor
@Getter
public enum AccountType {

    ADMIN("Admin", "커뮤니티 관리자"),
    GRANDMASTER("Honux77", "그랜드마스터 호눅스"),
    TUTOR("Tutor", "교수님"),
    ASSISTANT("Assistant", "조교"),
    NEWBIE("Newbie", "신입회원"),
    GUEST("Guest", "비회원");

    private static final Map<String, AccountType> typeMap = new HashMap<>();

    static {
        typeMap.put(ADMIN.getTypeKey(), ADMIN);
        typeMap.put(GRANDMASTER.getTypeKey(), GRANDMASTER);
        typeMap.put(TUTOR.getTypeKey(), TUTOR);
        typeMap.put(ASSISTANT.getTypeKey(), ASSISTANT);
        typeMap.put(NEWBIE.getTypeKey(), NEWBIE);
        typeMap.put(GUEST.getTypeKey(), GUEST);
    }

    private String typeKey;
    private String description;

    public static AccountType castFromString(String typeKey) {
        if (typeMap.containsKey(typeKey)) {
            return typeMap.get(typeKey);
        }
        throw new NoSuchAccountTypeException();
    }
}
