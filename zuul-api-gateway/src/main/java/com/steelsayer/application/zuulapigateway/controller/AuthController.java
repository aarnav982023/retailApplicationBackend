package com.steelsayer.application.zuulapigateway.controller;

import java.util.Map;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthController {
	@GetMapping("/checkSession")
	public Map<String, Object> checkSession(@AuthenticationPrincipal OAuth2User principal) {
		Map<String,Object> user =principal.getAttributes();
		user.remove("email");
		return user;
	}
}
