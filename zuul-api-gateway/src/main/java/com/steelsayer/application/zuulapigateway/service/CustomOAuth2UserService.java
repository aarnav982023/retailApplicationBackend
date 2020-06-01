package com.steelsayer.application.zuulapigateway.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.steelsayer.application.zuulapigateway.entity.UserPrincipal;
import com.steelsayer.application.zuulapigateway.proxy.UserServiceProxy;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {
	@Autowired
	private UserServiceProxy userServiceProxy;
	 @Override
	    public OAuth2User loadUser(OAuth2UserRequest oAuth2UserRequest) throws OAuth2AuthenticationException {
		 	System.out.println("Hello from loadUser");
	        OAuth2User oAuth2User = super.loadUser(oAuth2UserRequest);
	        try {
	            return processOAuth2User(oAuth2UserRequest, oAuth2User); 
	        } catch (Exception ex) {
	            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
	            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
	        }
	    }
	 
	 private OAuth2User processOAuth2User(OAuth2UserRequest oAuth2UserRequest, OAuth2User oAuth2User) {
		 Map<String,String> response = userServiceProxy.loginUser(oAuth2User);
		 return new UserPrincipal(response);
	 }
	 
}
