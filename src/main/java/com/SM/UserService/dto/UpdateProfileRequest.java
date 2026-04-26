package com.SM.UserService.dto;

public class UpdateProfileRequest {

    private String displayName;
    private String bio;
    private String country;
    private String language;
    private String avatarUrl;

    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }

    public String getBio() { return bio; }
    public void setBio(String bio) { this.bio = bio; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getLanguage() { return language; }
    public void setLanguage(String language) { this.language = language; }

    public String getAvatarUrl() { return avatarUrl; }
    public void setAvatarUrl(String avatarUrl) { this.avatarUrl = avatarUrl; }
}