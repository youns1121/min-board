package com.minboard.dto;

import com.minboard.enums.MemberMangeEnums;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {

    private Long memberSeq;

    @NotBlank(message = "아이디는 필수값 입니다.")
    private String memberId;

    @NotBlank(message = "패스워드는 필수값 입니다.")
    @Length(min=8, max=16, message = "비밀번호는 8자 이상, 16자 이하로 입력해주세요")
    private String memberPassword;

    @NotBlank(message = "이름은 필수값 입니다.")
    private String memberName;

    private String memberBirthday;

    @NotBlank(message = "성별은 필수값 입니다.")
    private String memberGender;

    private MemberMangeEnums.MemberRoleEnum memberRole;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    @Builder
    public MemberDto(Long memberSeq, String memberId, String memberPassword, String memberName, String memberBirthday, String memberGender) {
        this.memberSeq = memberSeq;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberBirthday = memberBirthday;
        this.memberGender = memberGender;
        this.memberRole = MemberMangeEnums.MemberRoleEnum.USER;
        this.createTime = LocalDateTime.now();
        this.updateTime = LocalDateTime.now();
    }
}
