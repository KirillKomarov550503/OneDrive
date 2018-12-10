package com.komarov.onedrive.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;

@EnableWebSecurity
@Configuration
@ComponentScan("com.komarov.onedrive")
public class SecurityConfig extends WebSecurityConfigurerAdapter {
  private static final String PREFIX = "/one-drive";
  @Autowired
  @Qualifier("customUserDetails")
  private UserDetailsService userDetailsService;

  @Autowired
  private CustomPasswordEncoder customPasswordEncoder;


  @Override
  protected void configure(AuthenticationManagerBuilder auth) throws Exception {
    auth.userDetailsService(userDetailsService).passwordEncoder(customPasswordEncoder);
  }

  @Override
  protected void configure(HttpSecurity http) throws Exception {
    http
        .authorizeRequests()
        .antMatchers(PREFIX + "/users/**").hasAnyAuthority("USER")
        .antMatchers(PREFIX + "/statistics/**").hasAuthority("ADMIN")
        .antMatchers(PREFIX + "/registration").anonymous()
        .and()
        .httpBasic()
        .and()
        .logout()
        .and()
        .sessionManagement()
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .csrf()
        .disable();
  }
}
