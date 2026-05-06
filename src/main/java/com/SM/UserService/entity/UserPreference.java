package com.SM.UserService.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_preferences")
public class UserPreference {

    @Id
    private String id;

    private Long userId;

    private String theme = "DARK";
    private String language = "en";

    private Boolean emailNotifications = true;

    private Boolean pushNotifications = true;

    private Boolean blurSensitive = true;

	public String getId() {
		return id;
	}

	public void setId(String id) {
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

	public UserPreference(String id, Long userId, String theme, String language, Boolean emailNotifications,
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