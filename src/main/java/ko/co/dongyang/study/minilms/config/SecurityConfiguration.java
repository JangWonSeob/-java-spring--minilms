package ko.co.dongyang.study.minilms.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http.csrf().disable();
        http.cors().disable();
        http.headers().frameOptions().sameOrigin();


        // 모든 페이지에 대해서 로그인 없이 접속 가능
        http.authorizeRequests()
                .antMatchers("/", "/**")
                .permitAll();

        super.configure(http);
    }
}
