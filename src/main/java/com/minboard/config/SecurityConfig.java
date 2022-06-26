//package com.minboard.config;
//
//import com.minboard.enums.MemberMangeEnums;
//import com.minboard.service.impl.MemberServiceImpl;
//import lombok.RequiredArgsConstructor;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.security.crypto.password.PasswordEncoder;
//
//
//@Configuration
//@EnableWebSecurity
//@RequiredArgsConstructor
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private final MemberServiceImpl memberService;
//
//    /** 로그인 설정 **/
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//
//        http.csrf().disable();
//        http.authorizeRequests()
//                //페이지 권한 설정
//                    .antMatchers("/admin/**").hasAuthority(MemberMangeEnums.MemberRoleEnum.ROLE_ADMIN.getKey())
//                    .anyRequest().permitAll()
//                .and()//로그인 설정
//                    .formLogin()
//                    .loginPage("/member/siginin") // 로그인 페이지 URL
//                    .loginProcessingUrl("/member/siginin") // 스프링 시큐리티가 낚아채서 대신 로그인 진행
//                    .defaultSuccessUrl("/board/category") //로그인 성공시 이동할 URL
//                    .usernameParameter("userName") // 로그인시  form에서 사용할 파라미터 이름으로 userName을 지정
//                    .failureUrl("/member/siginin") // 로그인 실패시 이동할 URL
//                .and()//로그아웃 설정
//                    .logout()
//                    .logoutSuccessUrl("/board/category")  //로그아웃 성공시 이동할 URL
//        ;
//
//    }
//
//    @Bean
//    public PasswordEncoder passwordEncoder(){
//
//        return new BCryptPasswordEncoder();
//    }
//
//    //시큐리티가 로그인 과정에서 password를 가로챌때 해당 해쉬로 암호화해서 비교
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.userDetailsService(memberService)
//                .passwordEncoder(passwordEncoder());
//    }
//}
