package com.minboard.service.common;

import com.minboard.mapper.MemberMapper;
import com.minboard.vo.member.MemberVo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {

    private final MemberMapper memberMapper;
    PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String memberId) throws UsernameNotFoundException {

        if(!StringUtils.hasText(memberId)){
            return null;
        }

        MemberVo findMember = memberMapper.findByMemberId(memberId);

        if(findMember == null){
            return null;
        }
        return User.builder()
                .username(memberId)
                .password(passwordEncoder.encode(findMember.getMemberPassword()))
                .roles(findMember.getMemberRole())
                .build();
    }
}
