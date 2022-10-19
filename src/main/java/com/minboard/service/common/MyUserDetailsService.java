package com.minboard.service.common;

import com.minboard.enums.MemberMangeEnums;
import com.minboard.mapper.MemberMapper;
import com.minboard.service.MemberService;
import com.minboard.vo.member.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;


@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        if(!StringUtils.hasText(memberId)){
            return null;
        }

        MemberVo member = memberMapper.findByMemberId(memberId);

        if(member == null){
            throw new UsernameNotFoundException(MemberMangeEnums.MemberAccessStatus.MEMBER_NOT_FOUND.getKey());
        }
        return User.builder()
                .username(memberId)
                .password(member.getMemberPassword())
                .roles(member.getMemberRole())
                .build();
    }




}
