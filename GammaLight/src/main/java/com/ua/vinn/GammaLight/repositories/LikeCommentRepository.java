package com.ua.vinn.GammaLight.repositories;


import com.ua.vinn.GammaLight.models.LikeComment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LikeCommentRepository extends JpaRepository<LikeComment, Long> {
    Optional<LikeComment> findByUserId(Long userId);
}
