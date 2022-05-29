package com.minboard.service.impl;

import com.minboard.dto.MemberDto;
import com.minboard.mapper.MemberMapper;
import com.minboard.service.MemberService;
import com.minboard.vo.member.MemberSaveVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.reflect.Member;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

     private final MemberMapper memberMapper;

     @Override
     public void insertMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {

          validateDuplicateMember(memberDto);

          MemberSaveVo memberSave = new MemberSaveVo();

          MemberDto member = memberSave.createMember(memberDto, passwordEncoder);

          memberMapper.insertMember(member);
     }

     @Override
     public void validateDuplicateMember(MemberDto memberDto) {
          MemberSaveVo findMember = memberMapper.findByUserName(memberDto);

          if(findMember != null){
               throw new IllegalStateException("이미 가입한 회원입니다.");
          }
     }
}
