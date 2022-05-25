package com.minboard.vo.member;

import com.minboard.enums.MemberRole;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class MemberSaveVo {

    private Long seq;

    private String userName;

    private String password;

    private String name;

    private String birthday;

    private String gender;

    private MemberRole memberRole;

    private LocalDateTime createTime;

    @Builder
    public MemberSaveVo(Long seq, String userName, String password,
                        String name, String birthday, String gender,
                        MemberRole memberRole, LocalDateTime createTime) {
        this.seq = seq;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.memberRole = memberRole;
        this.createTime = createTime;
    }
}
