package com.SM.UserService.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.SM.UserService.Repository.UserFollowingRepository;
import com.SM.UserService.Repository.UserProfileRepository;
import com.SM.UserService.dto.UpdateProfileRequest;
import com.SM.UserService.entity.UserFollowing;
import com.SM.UserService.entity.UserProfile;

import jakarta.transaction.Transactional;

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

        return profileRepo.save(profile);
    }

    public String followCreator(Long userId, Long creatorId) {

        if (followingRepo.existsByUserIdAndCreatorId(userId, creatorId)) {
            return "Already following creator";
        }

        UserFollowing f = new UserFollowing();
        f.setUserId(userId);
        f.setCreatorId(creatorId);

        followingRepo.save(f);

        return "Followed creator";
    }

    @Transactional
    public String unfollowCreator(Long userId, Long creatorId) {

        followingRepo.deleteByUserIdAndCreatorId(userId, creatorId);

        return "Unfollowed creator";
    }

    public List<UserFollowing> getFollowing(Long userId) {
        return followingRepo.findByUserId(userId);
    }
    
    public String createProfile(Long authUserId, String username) {

        if (profileRepo.findByAuthUserId(authUserId).isPresent()) {
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

        profileRepo.save(profile);

        return "Profile created";
    }
    
    public List<UserProfile> getAllUsers() {
        return profileRepo.findAll();
    }
}