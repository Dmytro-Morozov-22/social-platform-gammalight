package com.ua.vinn.GammaLight.services;

import com.ua.vinn.GammaLight.models.Comment;
import com.ua.vinn.GammaLight.models.LikeComment;
import com.ua.vinn.GammaLight.models.User;
import com.ua.vinn.GammaLight.repositories.LikeCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeCommentService {

    private final LikeCommentRepository likeCommentRepository;
    private final UserService userService;
    private final CommentService commentService;

    @Autowired
    public LikeCommentService(LikeCommentRepository likeCommentRepository, UserService userService, CommentService commentService) {
        this.likeCommentRepository = likeCommentRepository;
        this.userService = userService;
        this.commentService = commentService;
    }

    public long getNumberOfCommentLikes() {
        return likeCommentRepository.count();
    }

    public void putOrDeleteCommentLike(Long commentId) {
        User user = userService.getUserFromContext();
        likeCommentRepository.findByUserId(user.getId())
                .ifPresentOrElse(x -> deleteCommentLike(x.getId()), () -> putCommentLike(commentId, user));
    }

    private void putCommentLike(Long commentId, User user) {
        Comment comment = commentService.getCommentById(commentId);
        likeCommentRepository.save(new LikeComment(comment, user));
    }

    private void deleteCommentLike(Long likeCommentId) {
        likeCommentRepository.deleteById(likeCommentId);
    }

//test//test//test//test
//    public LikeComment testMethodReturnEntity(Long id) {
//        return likeCommentRepository2.findByUserId(id).get();
//    }
}