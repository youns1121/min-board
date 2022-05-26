package com.minboard.dto;

import com.minboard.enums.MemberRole;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberDto {

    private Long seq;

    private String userName;

    private String password;

    private String name;

    private String birthday;

    private String gender;

    private MemberRole memberRole;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delYn;

}
