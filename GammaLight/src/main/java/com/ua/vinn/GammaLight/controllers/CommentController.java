package com.ua.vinn.GammaLight.controllers;

import com.ua.vinn.GammaLight.dto.request.CommentRequestDto;
import com.ua.vinn.GammaLight.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/comment")
public class CommentController {

    private  final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<?> createComment(@RequestBody CommentRequestDto commentDto, @PathVariable Long id) {
        return ResponseEntity.ok(commentService.saveComment(commentDto.getComment(), id).getMessage());
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllCommentsOfUser() {
        return ResponseEntity.ok(commentService.getAllCommentsOfUser());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAllCommentsOfPost(@PathVariable Long id) {
        return ResponseEntity.ok(commentService.getAllCommentsOfPost(id));
    }

    //TODO - User can edit his own comment. Fix this!!!
    @PatchMapping("/edit/{id}")
    public void updateComment(@PathVariable Long id, @RequestBody CommentRequestDto commentDto) {
        commentService.updateComment(id, commentDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) {
        commentService.deleteCommentById(id);
    }

}