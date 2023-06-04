package com.ua.vinn.GammaLight.repositories;

import com.ua.vinn.GammaLight.models.Avatar;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AvatarRepository extends JpaRepository<Avatar, Long> {
}
