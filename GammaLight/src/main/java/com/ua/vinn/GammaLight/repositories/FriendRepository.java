package com.ua.vinn.GammaLight.repositories;

import com.ua.vinn.GammaLight.models.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FriendRepository extends JpaRepository<Friend, Long> {}
