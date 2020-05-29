package com.steelslayer.application.userservice.Entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class User {
	@Id
	@GeneratedValue
	private Long Id;
	private String email;
	private String name;
	private String socialId;
	private String avatarUrl;
	@Enumerated(EnumType.STRING)
	private SocialLoginType socialLoginType;
	public Long getId() {
		return Id;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSocialId() {
		return socialId;
	}
	public void setSocialId(String socialId) {
		this.socialId = socialId;
	}
	public SocialLoginType getSocialLoginType() {
		return socialLoginType;
	}
	public void setSocialLoginType(SocialLoginType socialLoginType) {
		this.socialLoginType = socialLoginType;
	}
	
	public String getAvatarUrl() {
		return avatarUrl;
	}
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	
	public User(String email, String name, String socialId, String avatarUrl, SocialLoginType socialLoginType) {
		super();
		this.email = email;
		this.name = name;
		this.socialId = socialId;
		this.avatarUrl = avatarUrl;
		this.socialLoginType = socialLoginType;
	}
	protected User() {}
}
