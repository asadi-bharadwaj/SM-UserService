package com.SM.UserService.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.SM.UserService.entity.UserProfile;

@Repository
public interface UserProfileRepository extends MongoRepository<UserProfile, String> {

    Optional<UserProfile> findByAuthUserId(Long authUserId);

    Optional<UserProfile> findByUsername(String username);

    boolean existsByUsername(String username);

}