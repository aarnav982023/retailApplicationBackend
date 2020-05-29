package com.steelsayer.application.zuulapigateway.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.steelsayer.application.zuulapigateway.proxy.UserServiceProxy;

@RestController
public class AuthController {
	@Autowired
	private UserServiceProxy userServiceProxy;
	@GetMapping("/checkSession")
	public Object checkSession(@AuthenticationPrincipal OAuth2User principal) {
		Object response = userServiceProxy.checkSession(principal);
		return response;
	}
}
