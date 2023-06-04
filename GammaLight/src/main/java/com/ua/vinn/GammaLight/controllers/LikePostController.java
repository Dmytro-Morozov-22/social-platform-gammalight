package com.ua.vinn.GammaLight.controllers;

import com.ua.vinn.GammaLight.dto.request.RegisterUserRequestDto;
import com.ua.vinn.GammaLight.services.LikePostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
public class LikePostController {

    private final LikePostService likePostService;

    @Autowired
    public LikePostController(LikePostService likePostService) {
        this.likePostService = likePostService;
    }

    @GetMapping("/post/put/{id}")
    public void putAvatarLike(@PathVariable Long id) {
        likePostService.putOrDeletePostLike(id);
    }

    @GetMapping("/post/count/{id}")
    public long  countAvatarLikes(@PathVariable Long id) {
        return likePostService.getNumberOfPostLikes(id);
    }

//test//test//test
//    @GetMapping("/post/count2/{id}")
//    public long  countAvatarLikes2(@PathVariable Long id) {
//        return likePostService.getNumberOfPostLikes2(id);
//    }
   // http://localhost:8012/like/post/count2/40


//    @GetMapping("/post/count2/{id}")
//    public ResponseEntity<?> userRegister(@PathVariable Long id) {
//        return ResponseEntity.ok(likePostService.testMethodReturnEntity(id));
//    }


}
