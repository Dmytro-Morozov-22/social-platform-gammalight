package com.ua.vinn.GammaLight.controllers;

import com.ua.vinn.GammaLight.services.LikeAvatarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class LikeAvatarController {

    private final LikeAvatarService likeAvatarService;

    @Autowired
    public LikeAvatarController(LikeAvatarService likeAvatarService) {
        this.likeAvatarService = likeAvatarService;
    }

    @GetMapping("/avatar/put/{id}")
    public void putAvatarLike(@PathVariable Long id) {
        likeAvatarService.putOrDeleteAvatarLike(id);
    }

    @GetMapping("/avatar/count")
    public long  countAvatarLikes() {
        return likeAvatarService.getNumberOfAvatarLikes();
    }

}
