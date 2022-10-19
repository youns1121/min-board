package com.minboard.vo.member;

import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;


import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Collections;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"memberSeq"})
public class MemberVo implements UserDetails {

    private Long memberSeq;

    private String memberId;

    private String memberPassword;

    private String memberName;

    private String memberBirthday;

    private String memberGender;

    private String memberRole;

    private LocalDateTime createTime;

    private LocalDateTime updateTime;

    private String delYn;

    @Builder
    public MemberVo(Long memberSeq, String memberId, String memberPassword, String memberName, String memberBirthday, String memberGender, String memberRole, LocalDateTime createTime, LocalDateTime updateTime, String delYn) {
        this.memberSeq = memberSeq;
        this.memberId = memberId;
        this.memberPassword = memberPassword;
        this.memberName = memberName;
        this.memberBirthday = memberBirthday;
        this.memberGender = memberGender;
        this.memberRole = memberRole;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.delYn = delYn;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.memberRole));
    }

    @Override
    public String getPassword() {
        return this.memberPassword;
    }

    @Override
    public String getUsername() {
        return this.memberId;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}