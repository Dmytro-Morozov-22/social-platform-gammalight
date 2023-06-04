package com.ua.vinn.GammaLight.services;

import com.ua.vinn.GammaLight.dto.request.SponsoredPostCreateDto;

import com.ua.vinn.GammaLight.models.Post;
import com.ua.vinn.GammaLight.models.SponsoredPost;
import com.ua.vinn.GammaLight.repositories.SponsoredPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SponsoredPostService {

    private final PostService postService;
    private final SponsoredPostRepository sponsoredPostRepository;
    private final UserService userService;

    @Autowired
    public SponsoredPostService(PostService postService, SponsoredPostRepository sponsoredPostRepository, UserService userService) {
        this.postService = postService;
        this.sponsoredPostRepository = sponsoredPostRepository;
        this.userService = userService;
    }

    public SponsoredPost createSponsoredPost(SponsoredPostCreateDto sponsoredDto){
        userService.isUserInDataBase(sponsoredDto.getSponsorId());
        Post post = postService.savePost(sponsoredDto);
        return sponsoredPostRepository.save(new SponsoredPost(post, sponsoredDto.getSponsorId()));
    }

    public List<Post> getAllSponsoredPostsOfUser(){
        Long id = userService.getUserFromContext().getId();
        return sponsoredPostRepository.findSponsoredPostsByUserId(id);
    }




}
