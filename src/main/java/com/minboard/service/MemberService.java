package com.minboard.service;

import com.minboard.dto.MemberDto;
import com.minboard.vo.member.MemberVo;

public interface MemberService {

     void insertMember(MemberDto memberDto);

     void validateDuplicateMember(MemberDto memberDto);

     void loginMember(MemberDto memberDto);

     MemberVo findByMemberId(String memberId);
}