package com.SM.UserService.controller;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.SM.UserService.dto.UpdateProfileRequest;
import com.SM.UserService.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/me")
    public ResponseEntity<?> myProfile(
            @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.ok(userService.getMyProfile(userId));
    }

    @PutMapping("/me")
    public ResponseEntity<?> updateProfile(
            @RequestHeader("X-User-Id") Long userId,
            @RequestBody UpdateProfileRequest req) {

        return ResponseEntity.ok(userService.updateProfile(userId, req));
    }

    @PostMapping("/follow/{creatorId}")
    public ResponseEntity<?> follow(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long creatorId) {

        return ResponseEntity.ok(
                userService.followCreator(userId, creatorId));
    }

    @DeleteMapping("/follow/{creatorId}")
    public ResponseEntity<?> unfollow(
            @RequestHeader("X-User-Id") Long userId,
            @PathVariable Long creatorId) {

        return ResponseEntity.ok(
                userService.unfollowCreator(userId, creatorId));
    }

    @GetMapping("/following")
    public ResponseEntity<?> following(
            @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.ok(userService.getFollowing(userId));
    }
    
    @PostMapping("/init-profile")
    public ResponseEntity<?> initProfile(@RequestBody Map<String,Object> req) {

        Long authUserId = Long.valueOf(req.get("authUserId").toString());
        String username = req.get("username").toString();

        return ResponseEntity.ok(
            userService.createProfile(authUserId, username)
        );
    }
    
    @GetMapping("/all")
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @GetMapping("/followers")
    public ResponseEntity<?> followers(
            @RequestHeader("X-User-Id") Long userId) {

        return ResponseEntity.ok(
            userService.getFollowers(userId)
        );
    }
}