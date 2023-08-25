package pd.example.demo.major.project.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import pd.example.demo.major.project.service.UserDetailsServiceImpl;

import java.util.concurrent.TimeUnit;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
    @Autowired
    AuthenticationSuccessHandler successHandler;

    private final UserDetailsServiceImpl userDetailsService;

    public WebSecurityConfig(UserDetailsServiceImpl userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService);
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }

    @Bean
    protected SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()

                .antMatchers("/list-of-candidates").hasRole("ADMIN")
                .antMatchers("/score").hasRole("ADMIN")
                .antMatchers("/proleap-register").hasRole("ADMIN")
                .antMatchers("/navigation-admin").hasRole("ADMIN")
                .anyRequest().permitAll()
                .and()
                .formLogin().permitAll()
                .loginPage("/login").successHandler(successHandler)
                .and()
                .rememberMe().tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(21)).key("secured")
                .and()
                .logout()
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout","GET"))
                .clearAuthentication(true).invalidateHttpSession(true)
                .deleteCookies("JSESSIONID").logoutSuccessUrl("/login?logout");
        return http.build();
    }
}
