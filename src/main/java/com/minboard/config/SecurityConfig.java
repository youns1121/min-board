package com.minboard.config;

import com.minboard.service.common.MyUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final MyUserDetailsService myUserDetailsService;

    /** 로그인 설정 **/
    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http
                .csrf().disable()
                .authorizeRequests()
                    .antMatchers("/board/**", "/member/**").permitAll()
                    .antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
//                    .anyRequest().authenticated() // 어떤 URI로 접근하던지 인증이 필요함을 설정합니다.
                .and()
                    .formLogin()
                    .loginPage("/member/siginin")
                    .loginProcessingUrl("/member/siginin")
                    .usernameParameter("memberId")
                    .passwordParameter("memberPassword")
                    .defaultSuccessUrl("/", true)
                    .permitAll()
                .and()
                    .logout()
                    .logoutRequestMatcher(new AntPathRequestMatcher("/member/logout"))
                    .logoutSuccessUrl("/")
                    .deleteCookies("JSESSIONID", "remember-me") // 로그아웃 후 쿠키 삭제
                    .invalidateHttpSession(true)
                ;
    }


    @Bean
    public PasswordEncoder passwordEncoder(){

        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/static/script/**","/static/css/**");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    //    //시큐리티가 로그인 과정에서 password를 가로챌때 해당 해쉬로 암호화해서 비교
//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//
//        auth.userDetailsService(memberService)
//                .passwordEncoder(passwordEncoder());
//    }
}
