package com.lidar.lidar;

import org.springframework.context.annotation.*;
import org.springframework.security.config.annotation.web.configuration.*;
import org.springframework.security.config.annotation.web.builders.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/test/**").hasIpAddress("localhost").and()
			.authorizeRequests()
				.regexMatchers("^/(?!test).*$").authenticated().and()
			.regexMatcher("^/(?!test).*$").requiresChannel()
				.anyRequest().requiresSecure();

	}
}
