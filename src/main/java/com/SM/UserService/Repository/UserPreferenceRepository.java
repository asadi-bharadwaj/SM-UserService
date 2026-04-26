package com.SM.UserService.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SM.UserService.entity.UserPreference;

@Repository
public interface UserPreferenceRepository extends JpaRepository<UserPreference, Long> {

    Optional<UserPreference> findByUserId(Long userId);
}