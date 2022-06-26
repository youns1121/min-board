package com.minboard.service;

import com.minboard.dto.MemberDto;

public interface MemberService {

//     void insertMember(MemberDto memberDto, PasswordEncoder passwordEncoder);

     void validateDuplicateMember(MemberDto memberDto);

     boolean isLoginMember(MemberDto memberDto);

}
