package com.ua.vinn.GammaLight.repositories;

import com.ua.vinn.GammaLight.models.LikeComment;
import com.ua.vinn.GammaLight.models.LikePost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikePostRepository extends JpaRepository<LikePost, Long> {
    Optional<LikePost> findByUserId(Long userId);


//    Optional<LikePost> findByUserId(Long userId);


    long countByPostId(Long postId);
}
