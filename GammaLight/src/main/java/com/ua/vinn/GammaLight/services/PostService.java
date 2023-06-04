package com.ua.vinn.GammaLight.services;

import com.ua.vinn.GammaLight.dto.request.PostCreateRequestDto;
import com.ua.vinn.GammaLight.dto.response.PostResponseDto;
import com.ua.vinn.GammaLight.models.Picture;
import com.ua.vinn.GammaLight.models.User;

import com.ua.vinn.GammaLight.models.Post;
import com.ua.vinn.GammaLight.models.securityElements.Role;
import com.ua.vinn.GammaLight.repositories.PostRepository;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class PostService {
    private final PostRepository postRepository;
    private final UserService userService;
    private final PictureService pictureService;
    private final Environment environment;

    @Autowired
    public PostService(PostRepository postRepository, UserService userService, PictureService pictureService, Environment environment) {
        this.postRepository = postRepository;
        this.userService = userService;
        this.pictureService = pictureService;
        this.environment = environment;
    }

    public Post savePost(PostCreateRequestDto postRequestDto) {
        Post savedPost = postRepository.save(new Post(postRequestDto.getPostText(), LocalDateTime.now(),
            userService.getUserFromContext()));
        if(postRequestDto.getPictures().get(0).getSize() > 0)
            this.savePostPicture(savedPost, postRequestDto.getPictures());
        return savedPost;
    }

    public void updatePost(Long id, PostCreateRequestDto postRequestDto) {
        Post post = getPostById(id);
        User user = userService.getUserFromContext();
        if(user.getId() == post.getUser().getId() || user.getRole().equals(Role.ADMINISTRATOR)) {
            post.setPostText(postRequestDto.getPostText());
            this.savePostPicture(postRepository.save(post), postRequestDto.getPictures());
        } else {
            throw new ResponseStatusException(
                    HttpStatus.FORBIDDEN,"Access to the post with id '" + id + "' is forbidden");
        }
    }

    public void savePostPicture(Post savedPost, List<MultipartFile> pictures){
        final String TARGET_FOLDER = "userPictures";
        for(MultipartFile picture : pictures)
            pictureService.savePicture(savedPost, picture, TARGET_FOLDER);
    }

    public byte[] getFirstPictureOfPost(Long id) throws IOException {
        Post post = getPostById(id);
        List<Picture> picture = post.getPicture();
        String path = picture.get(0).getPictureLink();
        InputStream in = new FileInputStream(path);
        return IOUtils.toByteArray(in);
    }

    public List<PostResponseDto> getAllPostsOfUser(){
        List<Post> posts = userService.getUserFromContext().getPost();
        return posts.stream()
            .map(x -> new PostResponseDto(x.getId(),x.getPostText(), x.getPublicationDate(),
                x.getPicture().stream().map(y -> y.getPictureLink()).collect(toList()))).collect(toList());
    }

    public void deletePostById(Long id) throws IOException {
        Post post = getPostById(id);
        User user = userService.getUserFromContext();
        if(user.getId() == post.getUser().getId() || user.getRole().equals(Role.ADMINISTRATOR)){
            pictureService.deletePictureFromDirectory(id);
            postRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,"Access to the post with id '" + id + "' is forbidden");
        }
    }

    public Post getPostById(Long postId){
        return postRepository.findById(postId).orElseThrow(
            () -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Comment with id '" +  postId + "' does not found"));
    }

}

