package com.ua.vinn.GammaLight.services;

import com.ua.vinn.GammaLight.models.LikePost;
import com.ua.vinn.GammaLight.models.User;
import com.ua.vinn.GammaLight.models.Post;
import com.ua.vinn.GammaLight.repositories.LikePostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikePostService {

    private final LikePostRepository likePostRepository;
    private final UserService userService;
    private final PostService postService;

    @Autowired
    public LikePostService(LikePostRepository likePostRepository, UserService userService, PostService postService) {
        this.likePostRepository = likePostRepository;
        this.userService = userService;
        this.postService = postService;
    }

    public long getNumberOfPostLikes(Long postId) {
        return likePostRepository.countByPostId(postId);
    }

    public void putOrDeletePostLike(Long postId) {
        User user = userService.getUserFromContext();
        likePostRepository.findByUserId(user.getId())
            .ifPresentOrElse(x -> deletePostLike(x.getId()), () -> putPostLike(postId, user));
    }

    private void putPostLike(Long postId, User user){
      Post post = postService.getPostById(postId);
        likePostRepository.save(new LikePost(user, post));
    }

    private void deletePostLike(Long likePostId){
        likePostRepository.deleteById(likePostId);
    }



//test//test//test//test//test
//    public long getNumberOfPostLikes2(Long postId) {
//        return likePostRepository2.countByEntityId(postId);
//    }

//    public LikePost testMethodReturnEntity(Long id){
//        LikePost likePost = likePostRepository2.findByUserId(id).get();
//        return likePost;
//    }

}
