package com.ua.vinn.GammaLight.controllers;

import com.ua.vinn.GammaLight.services.LikeCommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/like")
public class LikeCommentController {

    private final LikeCommentService likeCommentService;

    @Autowired
    public LikeCommentController(LikeCommentService likeCommentService) {
        this.likeCommentService = likeCommentService;
    }

    // TODO check the work of the like because when
    //  the same user puts like to two different comments the first like is deleted
    @GetMapping("/comment/put/{id}")
    public void putAvatarLike(@PathVariable Long id) {
        likeCommentService.putOrDeleteCommentLike(id);
    }

    @GetMapping("/comment/count")
    public long  countAvatarLikes() {
        return likeCommentService.getNumberOfCommentLikes();
    }




//test//test//test//test//test
//    @GetMapping("/comment/count2/{id}")
//    public ResponseEntity<?> userRegisters(@PathVariable Long id) {
//        return ResponseEntity.ok(likeCommentService.testMethodReturnEntity(id));
//    }
}
