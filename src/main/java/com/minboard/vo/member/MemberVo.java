package com.minboard.vo.member;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"seq"})
public class MemberVo {

    private Long memberSeq;

    private String memberId;

    private String memberPassword;

    private String memberName;

    private String memberBirthday;

    private String memberGender;

    private String memberRole;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delYn;

    @Builder
    public MemberVo(Long memberSeq, String memberId, String memberPassword, String memberName, String memberBirthday, String memberGender, String memberRole, LocalDateTime createTime, LocalDateTime updateTime, String delYn) {
        this.memberSeq = memberSeq;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberBirthday = memberBirthday;
        this.memberGender = memberGender;
        this.memberRole = memberRole;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delYn = delYn;
    }
}
