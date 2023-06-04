package com.ua.vinn.GammaLight.repositories;

import com.ua.vinn.GammaLight.models.Picture;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PictureRepository extends JpaRepository<Picture, Long> {
    List<Picture> getPictureByPostId(Long id);

    void deleteByPostId(Long id);
}
