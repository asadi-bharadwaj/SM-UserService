package com.SM.UserService.service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.SM.UserService.Repository.UserFollowingRepository;
import com.SM.UserService.Repository.UserProfileRepository;
import com.SM.UserService.dto.UpdateProfileRequest;
import com.SM.UserService.entity.UserFollowing;
import com.SM.UserService.entity.UserProfile;

@Service
public class UserService {

    private final UserProfileRepository profileRepo;
    private final UserFollowingRepository followingRepo;

    public UserService(UserProfileRepository profileRepo,
                       UserFollowingRepository followingRepo) {
        this.profileRepo = profileRepo;
        this.followingRepo = followingRepo;
    }

    public UserProfile getMyProfile(Long userId) {

        return profileRepo.findByAuthUserId(userId)
                .orElseGet(() -> {
                    UserProfile p = new UserProfile();
                    p.setAuthUserId(userId);
                    p.setDisplayName("New User");
                    p.setCreatedAt(LocalDateTime.now());
                    p.setUpdatedAt(LocalDateTime.now());
                    return profileRepo.save(p);
                });
    }

    public UserProfile updateProfile(Long userId, UpdateProfileRequest req) {

        UserProfile profile = getMyProfile(userId);

        if(req.getDisplayName() != null)
            profile.setDisplayName(req.getDisplayName());

        if(req.getBio() != null)
            profile.setBio(req.getBio());

        if(req.getCountry() != null)
            profile.setCountry(req.getCountry());

        if(req.getLanguage() != null)
            profile.setLanguage(req.getLanguage());

        if(req.getAvatarUrl() != null)
            profile.setAvatarUrl(req.getAvatarUrl());

        profile.setUpdatedAt(LocalDateTime.now());
        return profileRepo.save(profile);
    }

    public void updateAvatarUrl(Long userId, String avatarUrl) {
        UserProfile profile = getMyProfile(userId);
        profile.setAvatarUrl(avatarUrl);
        profile.setUpdatedAt(LocalDateTime.now());
        profileRepo.save(profile);
    }

    public String followCreator(Long userId, Long creatorId) {

        if (followingRepo.existsByUserIdAndCreatorId(userId, creatorId)) {
            return "Already following creator";
        }

        UserFollowing f = new UserFollowing();
        f.setUserId(userId);
        f.setCreatorId(creatorId);
        f.setCreatedAt(LocalDateTime.now());

        followingRepo.save(f);

        // Send Push Notification to Creator
        sendPushNotification(String.valueOf(creatorId), "PUSH", "New Subscriber!", "A new user started following you.");

        return "Followed creator";
    }

    private void sendPushNotification(String recipientId, String type, String title, String message) {
        try {
            RestTemplate restTemplate = new RestTemplate();
            Map<String, Object> body = new HashMap<>();
            body.put("recipientId", recipientId);
            body.put("type", type);
            body.put("title", title);
            body.put("message", message);

            restTemplate.postForObject(
                "http://localhost:8084/api/notifications",
                body,
                String.class
            );
        } catch (Exception e) {
            System.err.println("Notification failed: " + e.getMessage());
        }
    }

    public String unfollowCreator(Long userId, Long creatorId) {

        followingRepo.deleteByUserIdAndCreatorId(userId, creatorId);

        return "Unfollowed creator";
    }

    public List<UserFollowing> getFollowing(Long userId) {
        return followingRepo.findByUserId(userId);
    }
    
    public String createProfile(Long authUserId, String username) {

        UserProfile existingProfile = profileRepo.findByAuthUserId(authUserId).orElse(null);
        if (existingProfile != null) {
            boolean updated = false;
            if (existingProfile.getUsername() == null || existingProfile.getUsername().isBlank()) {
                existingProfile.setUsername(username);
                updated = true;
            }
            if (existingProfile.getDisplayName() == null
                    || existingProfile.getDisplayName().isBlank()
                    || "New User".equalsIgnoreCase(existingProfile.getDisplayName())) {
                existingProfile.setDisplayName(username);
                updated = true;
            }
            if (updated) {
                existingProfile.setUpdatedAt(LocalDateTime.now());
                profileRepo.save(existingProfile);
                return "Profile updated";
            }
            return "Profile already exists";
        }

        UserProfile profile = new UserProfile();
        profile.setAuthUserId(authUserId);
        profile.setUsername(username);
        profile.setDisplayName(username);
        profile.setBio("");
        profile.setAvatarUrl("");
        profile.setCountry("");
        profile.setLanguage("");
        profile.setCreatedAt(LocalDateTime.now());
        profile.setUpdatedAt(LocalDateTime.now());

        profileRepo.save(profile);

        return "Profile created";
    }
    
    public List<UserProfile> getAllUsers() {
        return profileRepo.findAll();
    }
    public List<UserFollowing> getFollowers(Long userId) {
        return followingRepo.findByCreatorId(userId);
    }
}