package com.minboard.service;

import com.minboard.dto.MemberDto;
import org.springframework.security.crypto.password.PasswordEncoder;

public interface MemberService {

     void insertMember(MemberDto memberDto, PasswordEncoder passwordEncoder);

     void validateDuplicateMember(MemberDto memberDto);

     boolean isLoginMember(MemberDto memberDto);
}