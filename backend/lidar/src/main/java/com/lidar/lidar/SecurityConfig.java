package com.lidar.lidar;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override

	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
			.authorizeRequests().anyRequest().permitAll();
				/*.antMatchers("/test/**").hasIpAddress("127.0.0.1").and()
			.authorizeRequests()
				.regexMatchers("^/(?!test).*$").permitAll();*/
	}
}

