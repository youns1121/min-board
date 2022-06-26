package com.minboard.vo.member;

import com.minboard.dto.MemberDto;
import com.minboard.enums.MemberMangeEnums;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"seq"})
public class MemberVo {

    private Long seq;

    private String userName;

    private String password;

    private String name;

    private String birthday;

    private String gender;

    private String memberRole;

    private LocalDateTime createTime;

    private String delYn;


    @Builder
    public MemberVo(Long seq, String userName, String password,
                    String name, String birthday, String gender,
                    String memberRole, LocalDateTime createTime, String delYn) {
        this.seq = seq;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.memberRole = memberRole;
        this.createTime = createTime;
        this.delYn = delYn;
    }

//    public MemberDto createMember(MemberDto memberDto, PasswordEncoder passwordEncoder){
//
//        MemberDto member = MemberDto.builder()
//                .userName(memberDto.getUserName())
//                .password(passwordEncoder.encode(memberDto.getPassword()))
//                .name(memberDto.getName())
//                .gender(memberDto.getGender())
//                .birthday(memberDto.getBirthday())
//                .createTime(LocalDateTime.now())
//                .memberRole(MemberMangeEnums.MemberRoleEnum.ROLE_USER.getKey())
//                .build();
//
//        return member;
//    }



}
