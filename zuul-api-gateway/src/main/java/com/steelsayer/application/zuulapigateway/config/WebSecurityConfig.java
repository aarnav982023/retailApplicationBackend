package com.steelsayer.application.zuulapigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.HttpStatusEntryPoint;
import org.springframework.security.web.authentication.logout.HttpStatusReturningLogoutSuccessHandler;

import com.steelsayer.application.zuulapigateway.service.CustomOAuth2UserService;
import com.steelsayer.application.zuulapigateway.service.CustomOidcUserService;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	@Autowired
	private CustomOAuth2UserService customOAuth2UserService;
	@Autowired
	private CustomOidcUserService customOidcUserService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.cors().and()
		.csrf().disable()
		.authorizeRequests(a -> a
				.antMatchers("/error").permitAll()
				.antMatchers("/**").authenticated())
				.exceptionHandling(e -> e
						.authenticationEntryPoint(new HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED)))
				.logout(l -> l.permitAll().logoutSuccessHandler(new HttpStatusReturningLogoutSuccessHandler()))
				.oauth2Login()
				.userInfoEndpoint()
				.userService(customOAuth2UserService).oidcUserService(customOidcUserService).and()
				.defaultSuccessUrl("http://localhost:3000");
	}
}
