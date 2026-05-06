package com.SM.UserService.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.SM.UserService.entity.UserFollowing;

@Repository
public interface UserFollowingRepository extends MongoRepository<UserFollowing, String> {

    List<UserFollowing> findByUserId(Long userId);

    Optional<UserFollowing> findByUserIdAndCreatorId(Long userId, Long creatorId);

    boolean existsByUserIdAndCreatorId(Long userId, Long creatorId);

    void deleteByUserIdAndCreatorId(Long userId, Long creatorId);
    
    List<UserFollowing> findByCreatorId(Long creatorId);

}