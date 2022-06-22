package com.minboard.vo.member;

import com.minboard.dto.MemberDto;
import com.minboard.enums.MemberMangeEnums;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;

@NoArgsConstructor
@Getter
@EqualsAndHashCode(of = {"seq"})
public class MemberVo implements UserDetails {

    private Long seq;

    private String userName;

    private String password;

    private String name;

    private String birthday;

    private String gender;

    private String memberRole;

    private LocalDateTime createTime;

    private String delYn;


    @Builder
    public MemberVo(Long seq, String userName, String password,
                    String name, String birthday, String gender,
                    String memberRole, LocalDateTime createTime, String delYn) {
        this.seq = seq;
        this.userName = userName;
        this.password = password;
        this.name = name;
        this.birthday = birthday;
        this.gender = gender;
        this.memberRole = memberRole;
        this.createTime = createTime;
        this.delYn = delYn;
    }

    public MemberDto createMember(MemberDto memberDto, PasswordEncoder passwordEncoder){

        MemberDto member = MemberDto.builder()
                .userName(memberDto.getUserName())
                .password(passwordEncoder.encode(memberDto.getPassword()))
                .name(memberDto.getName())
                .gender(memberDto.getGender())
                .birthday(memberDto.getBirthday())
                .createTime(LocalDateTime.now())
                .memberRole(MemberMangeEnums.MemberRoleEnum.ROLE_USER.getKey())
                .build();

        return member;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        ArrayList<GrantedAuthority> auth = new ArrayList<>();
        auth.add(new SimpleGrantedAuthority(memberRole));
        return auth;
    }

    @Override
    public String getUsername() {

        return this.userName;
    }

    public String getPassword() {
        return this.password;
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
