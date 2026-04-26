package com.SM.UserService.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@Table(name="user_following",
uniqueConstraints = {
 @UniqueConstraint(columnNames = {"user_id","creator_id"})
})
public class UserFollowing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="user_id", nullable=false)
    private Long userId;

    @Column(name="creator_id", nullable=false)
    private Long creatorId;

    @Column(name="created_at")
    private LocalDateTime createdAt;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
    }

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

	public UserFollowing(Long id, Long userId, Long creatorId, LocalDateTime createdAt) {
		super();
		this.id = id;
		this.userId = userId;
		this.creatorId = creatorId;
		this.createdAt = createdAt;
	}

	public UserFollowing() {
		super();
	}

    
}