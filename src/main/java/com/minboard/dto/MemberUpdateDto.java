package com.minboard.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class MemberUpdateDto {

    private Long memberSeq;

    private String memberId;

    private String memberPassword;

    private String memberName;

    private String memberBirthday;

    private String memberGender;

    private String memberRole;

    private LocalDateTime lastLoginTime;

    @Builder
    public MemberUpdateDto(Long memberSeq, String memberId, String memberPassword, String memberName, String memberBirthday, String memberGender, String memberRole, LocalDateTime lastLoginTime) {
        this.memberSeq = memberSeq;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberBirthday = memberBirthday;
        this.memberGender = memberGender;
        this.memberRole = memberRole;
        this.lastLoginTime = lastLoginTime;
    }
}
