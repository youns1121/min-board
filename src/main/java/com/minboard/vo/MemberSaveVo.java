package com.minboard.vo;

import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.time.LocalDateTime;

@Getter
public class MemberSaveVo {

    private Long id;

    @Email(message = "이메일 양식에 맞지 않습니다.")
    private String email;

    @NotBlank(message = "이름은 필수 입니다.")
    private String name;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String password;

    @Pattern(regexp = "(?=.*[0-9])(?=.*[a-zA-Z])(?=.*\\W)(?=\\S+$).{8,16}", message = "비밀번호는 8~16자 영문 대 소문자, 숫자, 특수문자를 사용하세요.")
    private String passwordVerify ;

    @Pattern(regexp = "^[0-9]+$", message = "숫자만 입력이 가능합니다.")
    private String phone;

    @Pattern(regexp = "^[0-9]+$", message = "숫자만 입력이 가능합니다.")
    private String birthday;

    @NotBlank(message = "성별을 선택 해주세요")
    private String gender;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delYn;

    @Builder
    public MemberSaveVo(String email, String name, String password, String passwordVerify, String phone, String birthday, String gender, LocalDateTime createTime, LocalDateTime updateTime, String delYn) {
        this.email = email;
        this.name = name;
        this.password = password;
        this.passwordVerify = passwordVerify;
        this.phone = phone;
        this.birthday = birthday;
        this.gender = gender;
        this.createTime = createTime.now();
        this.updateTime = updateTime.now();
        this.delYn = delYn;
    }
}
