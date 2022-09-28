package br.com.dsleite.cloudparking.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration{
    
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        
        http.authorizeRequests()
            .antMatchers(
                "/swagger-ui/*",
                "/swagger-resources/**",
                "/webjars/**",
                "/v3/api-docs",
                "/v3/api-docs/**",
                "/",
                "/csrf",
                "/*.js",
                "/*.css",
                "/*.ico",
                "/*.png"
            ).permitAll()
            .anyRequest()
            .authenticated()
            .and().httpBasic()
            .and()
            .csrf().disable()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

        return http.build();
    }
    
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
