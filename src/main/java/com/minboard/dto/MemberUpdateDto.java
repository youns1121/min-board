package com.minboard.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class MemberUpdateDto {

    private Long seq;

    private String userName;

    private String password;

    private String name;

    private String birthday;

    private String gender;

    private String memberRole;

    private LocalDateTime lastLoginTime;

    public MemberUpdateDto(Long seq, String userName, String password,
                           String name, String birthday, String gender,
                           String memberRole, LocalDateTime lastLoginTime) {
        this.seq = seq;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.memberRole = memberRole;
        this.lastLoginTime = lastLoginTime;
    }
}
