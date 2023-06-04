package com.ua.vinn.GammaLight.controllers;

import com.ua.vinn.GammaLight.services.FollowerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class FollowController {

    private final FollowerService followerService;

    public FollowController(FollowerService followerService) {
        this.followerService = followerService;
    }

    @GetMapping("/follower/all")
    public ResponseEntity<?> getAllNotification(){
        return ResponseEntity.ok(followerService.getAllFollowers());
    }

    @GetMapping("/follow/{followedUserId}")
    public void followUser(@PathVariable Long followedUserId) {
        followerService.follow(followedUserId);
    }

    @GetMapping("/unfollow/{followedUserId}")
    public void unfollowUser(@PathVariable Long followedUserId) {
        followerService.unfollow(followedUserId);
    }

}
