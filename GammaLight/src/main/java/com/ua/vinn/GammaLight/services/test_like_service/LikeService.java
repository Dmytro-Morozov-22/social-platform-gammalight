package com.ua.vinn.GammaLight.services.test_like_service;

import com.ua.vinn.GammaLight.models.User;
import com.ua.vinn.GammaLight.repositories.test_repositories.LikeRepository;
import com.ua.vinn.GammaLight.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeService {

    private final LikeRepository likeRepository;
    private final UserService userService;

    @Autowired
    public LikeService(LikeRepository likeRepository, UserService userService) {
        this.likeRepository = likeRepository;
        this.userService = userService;
    }


    public void putOrDeletePostLike(Long postId) {
        User user = userService.getUserFromContext();
      likeRepository.findByUserId(user.getId());

        if(likeRepository.findByUserId(user.getId()).isPresent()){

        }


    }

    private void putPostLike(Long postId, User user){
//        Post post = postService.getPostById(postId);
//        likeRepository.save(new LikePost(user, post));
    }

    private void deletePostLike(Long likePostId){
        likeRepository.deleteById(likePostId);
    }

}
