package com.steelsayer.application.zuulapigateway.entity;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

public class UserPrincipal implements OAuth2User {
	private Long id;
	private String email;
	private String name;
	private String avatarUrl;
	private String socialLoginType;
	private Map<String, Object> attributes;
	private Collection<? extends GrantedAuthority> authorities;
	 
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getSocialLoginType() {
		return socialLoginType;
	}

	public void setSocialLoginType(String socialLoginType) {
		this.socialLoginType = socialLoginType;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public Map<String, Object> getAttributes() {
		if (this.attributes == null) {
			this.attributes = new HashMap<>();
			this.attributes.put("id", this.getId());
			this.attributes.put("name", this.getName());
			this.attributes.put("socialLoginType", this.getSocialLoginType());
			this.attributes.put("email", this.getEmail());
			this.attributes.put("avatarUrl", this.getAvatarUrl());
		}
		return attributes;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		if(this.authorities==null) {
			this.authorities = Collections.
	                singletonList(new SimpleGrantedAuthority("ROLE_USER"));
		}
		return this.authorities;
	}

	@Override
	public String getName() {
		return this.name;
	}
	
	public UserPrincipal() {}
	
	public UserPrincipal(Map<String, String> user) {
		this.id= Long.parseLong(user.get("id"));
		this.email = user.get("email");
		this.avatarUrl=user.get("avatarUrl");
		this.name=user.get("name");
		this.socialLoginType=user.get("socialLoginType");
	}
	
}
