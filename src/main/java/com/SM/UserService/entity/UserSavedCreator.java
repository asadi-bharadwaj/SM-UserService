package com.SM.UserService.entity;

import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "user_saved_creators")
public class UserSavedCreator {

    @Id
    private String id;

    private Long userId;

    private Long creatorId;

    private LocalDateTime createdAt;

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

	public Long getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(Long creatorId) {
		this.creatorId = creatorId;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public UserSavedCreator(String id, Long userId, Long creatorId, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.creatorId = creatorId;
		this.createdAt = createdAt;
	}

	public UserSavedCreator() {
		super();
	}
    
    
}