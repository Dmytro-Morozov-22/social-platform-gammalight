package com.ua.vinn.GammaLight.repositories;

import com.ua.vinn.GammaLight.models.Post;
import com.ua.vinn.GammaLight.models.SponsoredPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SponsoredPostRepository extends JpaRepository<SponsoredPost, Long> {

    List<SponsoredPost> findBySponsorId(Long sponsorId);

    @Query(value = "SELECT * FROM sponsored_post sp INNER JOIN posts p ON sp.posts_id = p.id WHERE p.user_id = ?1",
           nativeQuery = true)
    List<Post> findSponsoredPostsByUserId(Long userId);







}
