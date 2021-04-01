package com.finshot.web.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityJavaConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    UserDetailsService userDetailsService;


    @Override
    public void configure(WebSecurity web) throws Exception {
        //예외 url을 설정한다.
        web.ignoring()
                .antMatchers("/css/**", "/js/**", "/images/**", "/lib/**");
    }



    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //사용자 세부설정을 위한 메소드
        auth.userDetailsService(userDetailsService)
        .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors().disable()		//cors방지
                .csrf().disable()		//csrf방지
//                .formLogin().disable()	//기본 로그인 페이지 없애기
//                .headers().frameOptions().disable()
//                .and()
                .authorizeRequests() //모두 인증이 되있어야한다.
                .antMatchers("/").permitAll()
                .antMatchers("/list").permitAll()
                .antMatchers("/find").permitAll()
                .antMatchers("/write").permitAll()
                .antMatchers("/writeOk").permitAll()
                .antMatchers("/login/login").permitAll()
                .antMatchers("/login/loginProcess").permitAll()
                .antMatchers("/login/join").permitAll()
                .antMatchers("/login/joinOK").permitAll()
                .antMatchers("/view").permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
//                .permitAll()
//                .loginPage("/login/login") 	// 로그인 페이지 url
                //.permitAll() //로그인 페이지와 로그인프로세스 페이지를 permitall을 넣어주어야한다.
                //.loginProcessingUrl("/login/loginProcess")  // view form의 action과 맞아야함
                //.permitAll()
                //.failureUrl("/login/fail") // 로그인 실패시 redirect
                //.defaultSuccessUrl("/list?cp=1", true) // 로그인 성공시
                .usernameParameter("gd")  // 로그인 요청시 id용 파라미터 (메소드 이름이 usernameParameter로 무조건 써야하지만, 파라미터는 email이든 id이든 name이든 상관없다.)
                .passwordParameter("dd")	// 로그인 요청시 password용 파라미터
            .and()
        // 3. 로그아웃 설정

//                .logout()
//                .logoutRequestMatcher(new AntPathRequestMatcher("/login/logout"))
//                .logoutSuccessUrl("/list?cp=1") // 로그아웃 성공시
//                .invalidateHttpSession(true)
        ;

    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }



}
