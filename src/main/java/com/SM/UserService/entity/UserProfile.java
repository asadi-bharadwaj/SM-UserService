package com.SM.UserService.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_profile")
public class UserProfile {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="auth_user_id", nullable=false, unique=true)
    private Long authUserId;

    @Column(name="display_name", length=100)
    private String displayName;

    @Column(unique=true, length=50)
    private String username;

    @Column(length=500)
    private String bio;

    @Column(name="avatar_url")
    private String avatarUrl;

    @Column(length=50)
    private String country;

    @Column(length=20)
    private String language;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @Column(name="updated_at")
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public UserProfile(Long id, Long authUserId, String displayName, String username, String bio, String avatarUrl,
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
