package ko.co.dongyang.study.minilms.config;

import ko.co.dongyang.study.minilms.user.service.UserService;
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
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    @Bean
    public PasswordEncoder getPasswordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationFailureHandler getAuthenticationFailureHandler(){
        return new UserAuthenticationFailureHandler();
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
                .antMatchers("favicon.ico","/res/**");

        super.configure(web);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.cors().disable();
        http.headers().frameOptions().sameOrigin();


        // 모든 페이지에 대해서 로그인 없이 접속 가능
        http.authorizeRequests()
                .antMatchers("/"
                        , "/user/register"
                        , "/user/register-complete"
                        , "/api/user/register.api"
                        , "/api/user/check-userid.api"
                )
                .permitAll();

        // 관리자 페이지, 관리자 권한 확인
        http.authorizeRequests()
                .antMatchers("/admin/**")
                .hasAnyAuthority("ROLE_ADMIN");

        // 로그인
        http.formLogin()
                // 권한이 없을 시 로그인 페이지로
                .loginPage("/user/login")
                // 실패시 처리
                .failureHandler(getAuthenticationFailureHandler())
                .permitAll();

        // 로그아웃
        http.logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/user/logout"))
                .logoutSuccessUrl("/")
                .invalidateHttpSession(true);

        // 관리자 권한이 없을 때 에러 페이지
        http.exceptionHandling()
                .accessDeniedPage("/common/denied");
        
        super.configure(http);
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        // 로그인 관련 페이지
        auth.userDetailsService(userService)
                .passwordEncoder(getPasswordEncoder());

        super.configure(auth);
    }

}

