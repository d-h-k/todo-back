package com.dong.account.entity;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Getter
public class Account {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id")
    private Long id;

    @NotBlank
    private String nickname;
    //todo : 유니크 제약조건 걸어줘야 함, 인덱스태우면 더 좋고

    @NotNull
    @Column(name = "account_type")
    @Enumerated(EnumType.STRING)
    private AccountType accountType;

    @Column(name = "authentication")
    private String authentication;

    @NotNull
    private Boolean quit;

}
