package com.steelsayer.application.zuulapigateway.service;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;

import com.steelsayer.application.zuulapigateway.entity.UserPrincipalOidc;
import com.steelsayer.application.zuulapigateway.proxy.UserServiceProxy;

@Service
public class CustomOidcUserService extends OidcUserService {
	@Autowired
	private UserServiceProxy userServiceProxy;
	 @Override
	    public OidcUser loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
		 OidcUser oidcUser = super.loadUser(userRequest);
	        try {
	            return processOAuth2User(userRequest, oidcUser); 
	        } catch (Exception ex) {
	            // Throwing an instance of AuthenticationException will trigger the OAuth2AuthenticationFailureHandler
	            throw new InternalAuthenticationServiceException(ex.getMessage(), ex.getCause());
	        }
	    }
	 
	 private OidcUser processOAuth2User(OidcUserRequest userRequest, OidcUser oidcUser) {
		 Map<String,String> response = userServiceProxy.loginUser(oidcUser);
		 return new UserPrincipalOidc(response,oidcUser);
	 }
}
