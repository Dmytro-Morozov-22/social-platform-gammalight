package com.ua.vinn.GammaLight.repositories;

import com.ua.vinn.GammaLight.models.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
    void deleteByUserId(Long userId);
    List<Notification> findAllByFollowedUserId(Long followedUserId);
}
