package rw.ac.rca.auth.config;

import org.springframework.security.config.annotation.SecurityBuilder;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableWebSecurity
public abstract class SecurityConfigurer implements WebSecurityConfigurer {

    @Override
    public void init(SecurityBuilder builder) throws Exception {

    }
    @Override
    public void configure(SecurityBuilder builder) throws Exception {
        HttpSecurity http = (HttpSecurity) builder;
        http
                .authorizeHttpRequests()
                .anyRequest()
                .permitAll();
    }
}
