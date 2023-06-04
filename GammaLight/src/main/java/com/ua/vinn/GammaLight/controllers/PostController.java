package com.ua.vinn.GammaLight.controllers;

import com.ua.vinn.GammaLight.dto.request.PostCreateRequestDto;
import com.ua.vinn.GammaLight.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/post")
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createPost(@ModelAttribute PostCreateRequestDto postRequestDto) {
        return ResponseEntity.ok(postService.savePost(postRequestDto));
    }

    @GetMapping(value = "/{id}", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<?> pictureOfPost(@PathVariable Long id) throws IOException {
        return ResponseEntity.ok(postService.getFirstPictureOfPost(id));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getPostsOfUser() {
        return ResponseEntity.ok(postService.getAllPostsOfUser());
    }

    // TODO - User can edit his own post. Fix this!!!
    @PatchMapping("/edit/{id}")
    public void updatePost(@PathVariable Long id, @ModelAttribute PostCreateRequestDto postRequestDto) {
        postService.updatePost(id, postRequestDto);
    }

    @DeleteMapping("/delete/{id}")
    public void deletePost(@PathVariable Long id) throws IOException {
       postService.deletePostById(id);
    }
}
