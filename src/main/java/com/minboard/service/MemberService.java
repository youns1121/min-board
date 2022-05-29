package com.minboard.service;

import com.minboard.dto.MemberDto;
import com.minboard.mapper.MemberMapper;
import com.minboard.vo.member.MemberSaveVo;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

public interface MemberService {

     void insertMember(MemberDto memberDto, PasswordEncoder passwordEncoder);

     void validateDuplicateMember(MemberDto memberDto);
}
