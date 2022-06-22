package com.minboard.service.impl;

import com.minboard.dto.MemberDto;
import com.minboard.enums.MemberMangeEnums;
import com.minboard.mapper.MemberMapper;
import com.minboard.service.MemberService;
import com.minboard.vo.member.MemberVo;
import lombok.RequiredArgsConstructor;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberServiceImpl implements MemberService, UserDetailsService {

     private final MemberMapper memberMapper;

     @Override
     public void insertMember(MemberDto memberDto, PasswordEncoder passwordEncoder) {

          validateDuplicateMember(memberDto);

          MemberVo memberSave = new MemberVo();

          MemberDto member = memberSave.createMember(memberDto, passwordEncoder);

          memberMapper.insertMember(member);
     }

     @Override
     public void validateDuplicateMember(MemberDto member) {
          MemberVo findMember = memberMapper.findByUserName(member.getUserName());

          if(findMember != null){
               throw new IllegalStateException(MemberMangeEnums.MemberAccessStatus.MEMBER_EXIST.getKey());
          }
     }
     
     @Override
     public boolean isLoginMember(MemberDto memberDto) {

          if(!memberMapper.isLoginMember(memberDto)){
               throw new IllegalStateException(MemberMangeEnums.MemberAccessStatus.MEMBER_SIGININ_FAIL.getKey());
          }

          return true;
     }

     @Override
     public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
          MemberVo findMember = memberMapper.findByUserName(userName);

          if(findMember == null){
               throw new UsernameNotFoundException(MemberMangeEnums.MemberAccessStatus.MEMBER_NOT_FOUND.getKey());
          }

          return findMember;
     }


}
