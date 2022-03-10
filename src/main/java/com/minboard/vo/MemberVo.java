package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
public class MemberVo {

    private Long id;

    private String email;

    private String password;

    private String birthday;

    private String gender;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delYn;

    @Builder
    public MemberVo(String email, String password, String birthday, String gender, LocalDateTime createTime, LocalDateTime updateTime, String delYn) {
        this.email = email;
        this.password = password;
        this.birthday = birthday;
        this.gender = gender;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delYn = delYn;
    }
}
