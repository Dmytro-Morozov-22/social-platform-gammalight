package com.ua.vinn.GammaLight.repositories;

import com.ua.vinn.GammaLight.models.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findPostByUserId(Long id);
}
