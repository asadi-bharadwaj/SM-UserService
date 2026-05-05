package com.SM.UserService.controller;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Map;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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
            @RequestBody UpdateProfileRequest request) {

        return ResponseEntity.ok(userService.updateProfile(userId, request));
    }

    @PostMapping("/me/avatar")
    public ResponseEntity<?> uploadAvatar(
            @RequestHeader("X-User-Id") Long userId,
            @RequestParam("file") MultipartFile file) {

        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("File is empty");
        }

        try {
            // Create uploads directory if it doesn't exist
            Path uploadDir = Paths.get("uploads/avatars");
            if (!Files.exists(uploadDir)) {
                Files.createDirectories(uploadDir);
            }

            // Generate unique filename
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename != null && originalFilename.contains(".")
                ? originalFilename.substring(originalFilename.lastIndexOf("."))
                : ".jpg";
            String filename = userId + "_" + UUID.randomUUID().toString() + extension;

            // Save file
            Path filePath = uploadDir.resolve(filename);
            Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);

            // Generate URL (assuming the service is accessible at localhost:8081)
            String avatarUrl = "http://localhost:8081/uploads/avatars/" + filename;

            // Update user's avatar URL
            userService.updateAvatarUrl(userId, avatarUrl);

            return ResponseEntity.ok(Map.of("avatarUrl", avatarUrl));

        } catch (IOException e) {
            return ResponseEntity.internalServerError().body("Failed to upload file: " + e.getMessage());
        }
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