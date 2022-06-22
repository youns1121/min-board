package com.minboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class MemberSiginInDto {

    private Long seq;

    @NotBlank(message = "아이디는 필수값 입니다.")
    private String userName;

    @NotBlank(message = "패스워드는 필수값 입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String password;

    @Builder
    public MemberSiginInDto(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


}
