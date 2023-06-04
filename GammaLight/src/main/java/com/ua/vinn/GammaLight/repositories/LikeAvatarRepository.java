package com.ua.vinn.GammaLight.repositories;

import com.ua.vinn.GammaLight.models.LikeAvatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeAvatarRepository extends JpaRepository<LikeAvatar, Long> {
    Optional<LikeAvatar> findByUserId(Long userId);
}
