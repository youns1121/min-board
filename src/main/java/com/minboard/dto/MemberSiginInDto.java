package com.minboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@Getter
@Setter
public class MemberSiginInDto {

    private Long memberSeq;

    @NotBlank(message = "아이디는 필수값 입니다.")
    private String memberId;

    @NotBlank(message = "패스워드는 필수값 입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력 해 주세요")
    private String memberPassword;

    @Builder
    public MemberSiginInDto(Long memberSeq, String memberId, String memberPassword) {
        this.memberSeq = memberSeq;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
    }
}