package com.ua.vinn.GammaLight.repositories;

import com.ua.vinn.GammaLight.models.Follower;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FollowerRepository extends JpaRepository<Follower, Long> {
    void deleteByFollowedUserId(Long followedUserId);
    List<Follower> findAllByFollowedUserId(Long followedUserId);
}
