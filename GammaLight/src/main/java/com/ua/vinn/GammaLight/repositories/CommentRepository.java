package com.ua.vinn.GammaLight.repositories;

import com.ua.vinn.GammaLight.models.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}
