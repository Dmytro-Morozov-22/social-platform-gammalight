package com.ua.vinn.GammaLight.repositories.test_repositories;

import com.ua.vinn.GammaLight.models.LikePost;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikePostRepository2 extends LikeRepository<LikePost, Long> {

    @Override
   Optional<LikePost> findByUserId(Long userId);
}
