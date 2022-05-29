package com.minboard.vo.member;

import com.minboard.dto.MemberDto;
import com.minboard.enums.MemberMangeEnums;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
@EqualsAndHashCode(of = {"seq"})
public class MemberSaveVo {

    private Long seq;

    private String userName;

    private String password;

    private String name;

    private String birthday;

    private String gender;

    private String memberRole;

    private LocalDateTime createTime;

    public MemberDto createMember(MemberDto memberDto, PasswordEncoder passwordEncoder){

        MemberDto member = MemberDto.builder()
                .userName(memberDto.getUserName())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .name(memberDto.getName())
                .gender(memberDto.getGender())
                .birthday(memberDto.getBirthday())
                .createTime(LocalDateTime.now())
                .memberRole(MemberMangeEnums.MemberRoleEnum.USER.getTitle())
                .build();

        return member;
    }
}
