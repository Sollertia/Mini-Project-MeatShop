package com.hanghae.miniprojectmeatshop.security;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity // 스프링 Security 지원을 가능하게 함
@EnableGlobalMethodSecurity(securedEnabled = true) // @Secured 어노테이션 활성화
@AllArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final JwtTokenProvider jwtTokenProvider;

    @Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }


    @Bean
    public BCryptPasswordEncoder encodePassword() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource(){
        CorsConfiguration configuration = new CorsConfiguration();

        configuration.addAllowedOriginPattern("*");
        configuration.addAllowedHeader("*");
        configuration.addAllowedMethod("*");
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**",configuration);
        return source;

    }
    @Override
    public void configure(WebSecurity web) {
// h2-console 사용에 대한 허용 (CSRF, FrameOptions 무시)
        web
                .ignoring()
                .antMatchers("/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .httpBasic().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests() // 요청에 대한 사용권한 체크
                .antMatchers("/images/**").permitAll()
                .antMatchers("/css/**").permitAll()
                .antMatchers("/user/**").permitAll()
                .antMatchers("/h2-console/**").permitAll()
                .antMatchers("/").permitAll()
                .antMatchers("/**").permitAll()
                .and()
                .addFilterBefore(new JwtAuthenticationFilter(jwtTokenProvider),
                        UsernamePasswordAuthenticationFilter.class)
                .cors();
//                .and()
//                //// [로그인 기능]
//                .formLogin()
//                .failureHandler(userLoginFailHandler)
//
//// 로그인 View 제공 (GET /user/login)
////                .loginPage("/user/login")
//// 로그인 처리 (POST /user/login)
//                .loginProcessingUrl("/user/login")
//// 로그인 처리 후 성공 시 URL
//                .defaultSuccessUrl("http://52.79.248.107:3000/")
//// 로그인 처리 후 실패 시 URL
//                .permitAll()
//                .successHandler(loginSuccessHandler)
//                .and()
//// [로그아웃 기능]
//                .logout()
//// 로그아웃 요청 처리 URL
//                .logoutUrl("/user/logout")
//                .permitAll()
//                .and()
//                .exceptionHandling()
//// "접근 불가" 페이지 URL 설정
//                .accessDeniedPage("/forbidden.html");//        http.authorizeRequests()
//////                .anyRequest().authenticated()
//////                .and()
////                .antMatchers(HttpMethod.OPTIONS,"/**").permitAll()
////                .antMatchers("/user/login").permitAll()
////
////                .and()
////                .cors()
////                .and()
//////// image 폴더를 login 없이 허용
//////                .antMatchers("/image/**").permitAll()
//////// css 폴더를 login 없이 허용
//////                .antMatchers("/css/**").permitAll()
//////// 회원 관리 처리 API 전부를 login 없이 허용
//////                .antMatchers("/user/**").permitAll()
//////// 그 외 어떤 요청이든 '인증'
//////                .antMatchers("/write.html").authenticated()
////////                .antMatchers("/api/comment").authenticated()
//////                .antMatchers("/api/deletecomment/*").authenticated()
//////                .antMatchers("/api/editcomment").authenticated()
//////                .and()

    }
}