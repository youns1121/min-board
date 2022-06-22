package com.minboard.mapper;

import com.minboard.dto.MemberDto;
import com.minboard.dto.MemberUpdateDto;
import com.minboard.vo.member.MemberVo;
import org.apache.ibatis.annotations.Mapper;

import java.time.LocalDateTime;

@Mapper
public interface MemberMapper {

    void insertMember(MemberDto memberDto);

    MemberVo findByUserName(String userName);

    boolean isLoginMember(MemberDto memberDto);

    void updateMemberLastLogin(MemberUpdateDto memberUpdateDto);
}
