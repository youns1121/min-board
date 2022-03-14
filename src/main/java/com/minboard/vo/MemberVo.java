package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
public class MemberVo {

    private Long id;

    @NotBlank(message = "이메일은 필수값 입니다.")
    private String email;

    private String name;

    @NotBlank(message = "패스워드는 필수값 입니다.")
    private String password;

    private String phone;

    private String birthday;

    private String gender;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delYn;

    @Builder
    public MemberVo(String email, String name, String password, String phone, String birthday, String gender, LocalDateTime createTime, LocalDateTime updateTime, String delYn) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delYn = delYn;
    }
}
