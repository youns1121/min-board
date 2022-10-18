package com.minboard.service.impl;

import com.minboard.dto.MemberDto;
import com.minboard.enums.MemberMangeEnums;
import com.minboard.mapper.MemberMapper;
import com.minboard.service.MemberService;
import com.minboard.vo.member.MemberVo;
import lombok.RequiredArgsConstructor;

import org.apache.ibatis.javassist.bytecode.DuplicateMemberException;
import org.omg.PortableInterceptor.ORBInitInfoPackage.DuplicateName;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.ValidationException;


@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

     private final MemberMapper memberMapper;

     @Override
     @Transactional
     public void insertMember(MemberDto memberDto) {

          validateDuplicateMember(memberDto);
          BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
          memberDto.setMemberPassword(passwordEncoder.encode(memberDto.getMemberPassword()));
          memberMapper.insertMember(memberDto);
     }

     @Override
     @Transactional(readOnly = true)
     public void validateDuplicateMember(MemberDto member) {

          MemberVo findMember = memberMapper.findByMemberId(member.getMemberId());
          if(findMember != null){
               throw new ValidationException(MemberMangeEnums.MemberAccessStatus.MEMBER_EXIST.getKey());
          }
     }
     
     @Override
     @Transactional
     public void loginMember(MemberDto member) {

          BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
          member.setMemberPassword(passwordEncoder.encode(member.getMemberPassword()));
          member.setMemberRole(MemberMangeEnums.MemberRoleEnum.USER);
          if(!memberMapper.isLoginMember(member)){
               throw new IllegalStateException(MemberMangeEnums.MemberAccessStatus.MEMBER_SIGININ_FAIL.getKey());
          }

     }

     @Override
     @Transactional(readOnly = true)
     public MemberVo findByMemberId(String memberId) {

          MemberVo member = memberMapper.findByMemberId(memberId);
          if(member == null){
               throw new UsernameNotFoundException(MemberMangeEnums.MemberAccessStatus.MEMBER_NOT_FOUND.getKey());
          }
          return member;
     }
}
