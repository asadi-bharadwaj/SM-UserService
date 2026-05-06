package com.SM.UserService.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "users_profile")
public class UserProfile {

	@Id
    private String id;

    private Long authUserId;

    private String displayName;

    private String username;

    private String bio;

    private String avatarUrl;

    private String country;

    private String language;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Long getAuthUserId() {
		return authUserId;
	}

	public void setAuthUserId(Long authUserId) {
		this.authUserId = authUserId;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getBio() {
		return bio;
	}

	public void setBio(String bio) {
		this.bio = bio;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public LocalDateTime getUpdatedAt() {
		return updatedAt;
	}

	public void setUpdatedAt(LocalDateTime updatedAt) {
		this.updatedAt = updatedAt;
	}

	public UserProfile(String id, Long authUserId, String displayName, String username, String bio, String avatarUrl,
			String country, String language, LocalDateTime createdAt, LocalDateTime updatedAt) {
		super();
		this.id = id;
		this.authUserId = authUserId;
		this.displayName = displayName;
		this.username = username;
		this.bio = bio;
		this.avatarUrl = avatarUrl;
		this.country = country;
		this.language = language;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
	}

	public UserProfile() {
		super();
	}
    
    
}
