//package com.handler;
//
//import com.minboard.dto.MemberUpdateDto;
//import com.minboard.mapper.MemberMapper;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.ServletException;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.time.LocalDateTime;
//
//@RequiredArgsConstructor
//@Component
//public class AuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
//
//    private final MemberMapper memberMapper;
//
//    @Override
//    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
//                                        Authentication authentication) throws IOException, ServletException {
//
//        MemberUpdateDto memberUpdate = new MemberUpdateDto();
//
//        memberUpdate.setUserName(authentication.getName());
//        memberUpdate.setLastLoginTime(LocalDateTime.now());
//
//        memberMapper.updateMemberLastLogin(memberUpdate);
//
//        setDefaultTargetUrl("/board/category");
//
//        super.onAuthenticationSuccess(request, response, authentication);
//    }
//
//}
