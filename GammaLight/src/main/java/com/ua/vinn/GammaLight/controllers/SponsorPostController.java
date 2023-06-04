package com.ua.vinn.GammaLight.controllers;

import com.ua.vinn.GammaLight.dto.request.PostCreateRequestDto;
import com.ua.vinn.GammaLight.dto.request.SponsoredPostCreateDto;
import com.ua.vinn.GammaLight.services.SponsoredPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sponsor-post")
public class SponsorPostController {

    private final SponsoredPostService sponsoredPostService;

    @Autowired
    public SponsorPostController(SponsoredPostService sponsoredPostService) {
        this.sponsoredPostService = sponsoredPostService;
    }

    @PostMapping("/create")
    public ResponseEntity<?> createSponsorPost(@ModelAttribute SponsoredPostCreateDto sponsoredDto){
        return ResponseEntity.ok(sponsoredPostService.createSponsoredPost(sponsoredDto));
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllSponsoredPostOfUser(){
        return ResponseEntity.ok(sponsoredPostService.getAllSponsoredPostsOfUser());
    }



}
