package com.SM.UserService.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.SM.UserService.entity.UserSavedCreator;

@Repository
public interface UserSavedCreatorRepository extends JpaRepository<UserSavedCreator, Long> {

    List<UserSavedCreator> findByUserId(Long userId);

    Optional<UserSavedCreator> findByUserIdAndCreatorId(Long userId, Long creatorId);

    boolean existsByUserIdAndCreatorId(Long userId, Long creatorId);

    void deleteByUserIdAndCreatorId(Long userId, Long creatorId);
}