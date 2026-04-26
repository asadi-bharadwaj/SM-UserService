package com.SM.UserService.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="user_preferences")
public class UserPreference {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", unique=true, nullable=false)
    private Long userId;

    private String theme = "DARK";
    private String language = "en";

    @Column(name="email_notifications")
    private Boolean emailNotifications = true;

    @Column(name="push_notifications")
    private Boolean pushNotifications = true;

    @Column(name="blur_sensitive")
    private Boolean blurSensitive = true;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getTheme() {
		return theme;
	}

	public void setTheme(String theme) {
		this.theme = theme;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Boolean getEmailNotifications() {
		return emailNotifications;
	}

	public void setEmailNotifications(Boolean emailNotifications) {
		this.emailNotifications = emailNotifications;
	}

	public Boolean getPushNotifications() {
		return pushNotifications;
	}

	public void setPushNotifications(Boolean pushNotifications) {
		this.pushNotifications = pushNotifications;
	}

	public Boolean getBlurSensitive() {
		return blurSensitive;
	}

	public void setBlurSensitive(Boolean blurSensitive) {
		this.blurSensitive = blurSensitive;
	}

	public UserPreference(Long id, Long userId, String theme, String language, Boolean emailNotifications,
			Boolean pushNotifications, Boolean blurSensitive) {
		super();
		this.id = id;
		this.userId = userId;
		this.theme = theme;
		this.language = language;
		this.emailNotifications = emailNotifications;
		this.pushNotifications = pushNotifications;
		this.blurSensitive = blurSensitive;
	}

	public UserPreference() {
		super();
	}

    
}