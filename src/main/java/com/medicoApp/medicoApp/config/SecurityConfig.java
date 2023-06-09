package com.medicoApp.medicoApp.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private PasswordEncoder passwordEncoder ;


    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/for-user")
                .hasAnyRole("USER","ADMIN")
                .antMatchers("/offer").permitAll()
                .anyRequest().permitAll();
    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.jdbcAuthentication()
                .dataSource(dataSource)
                .usersByUsernameQuery("SELECT u.user_name, u.user_password, 1 " +
                        "FROM users u WHERE u.user_name = ?")
                .authoritiesByUsernameQuery("SELECT u.user_name, u.role " +
                        "FROM users u " +
                        "WHERE u.user_name = ?")
                .passwordEncoder(passwordEncoder);
    }
}
