package com.ua.vinn.GammaLight.services;

import com.ua.vinn.GammaLight.dto.request.CommentRequestDto;
import com.ua.vinn.GammaLight.dto.response.MessageResponseDto;
import com.ua.vinn.GammaLight.models.Comment;

import com.ua.vinn.GammaLight.models.Post;
import com.ua.vinn.GammaLight.models.User;
import com.ua.vinn.GammaLight.models.securityElements.Role;
import com.ua.vinn.GammaLight.repositories.CommentRepository;
import com.ua.vinn.GammaLight.repositories.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    public final CommentRepository commentRepository;
    public final UserService userService;
    public final PostRepository postRepository;

    @Autowired
    public CommentService(CommentRepository commentRepository, UserService userService, PostRepository postRepository) {
        this.commentRepository = commentRepository;
        this.userService = userService;
        this.postRepository = postRepository;
    }

    public MessageResponseDto saveComment(String comment, Long id){
        User user = userService.findUserByEmail(userService.getCurrentUser());
        Post post = postRepository.getById(id);
        Comment savedComment = commentRepository.save(new Comment(comment, LocalDateTime.now(), user, post));
        return new MessageResponseDto(savedComment.getText());
    }

    public List<Comment> getAllCommentsOfUser(){
      return userService.findUserByEmail(userService.getCurrentUser()).getComment();
    }

    public List<Comment> getAllCommentsOfPost(Long id){
        return postRepository.findById(id).get().getComment();
    }

    public void deleteCommentById(Long id) {
        Comment comment = commentRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Post with id '"+id+"' was not found"));
        User user = userService.findUserByEmail(userService.getCurrentUser());

        if(user.getId() == comment.getUser().getId() || user.getRole().equals(Role.ADMINISTRATOR)){
            commentRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,"Access to the comment with id '" + id + "' is forbidden");
        }
    }

    public void updateComment(Long id, CommentRequestDto commentDto) {
        Comment comment = commentRepository.findById(id).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Comment with id '"+id+"' was not found"));
        comment.setText(commentDto.getComment());
        commentRepository.save(comment);
    }

    public Comment getCommentById(Long commentId){
        return commentRepository.findById(commentId).orElseThrow(
            () -> new ResponseStatusException(
                HttpStatus.NOT_FOUND,"Comment with id '" +  commentId + "' does not found"));
    }

}
