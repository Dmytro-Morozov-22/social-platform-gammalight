package com.ua.vinn.GammaLight.controllers;

import com.ua.vinn.GammaLight.services.AvatarService;
import com.ua.vinn.GammaLight.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/avatar")
public class AvatarController {

    private final AvatarService avatarService;
    private final UserService userService;

    @Autowired
    public AvatarController(AvatarService avatarService, UserService userService) {
        this.avatarService = avatarService;
        this.userService = userService;
    }

    @PostMapping("/add")
    public void addAvatar(@ModelAttribute MultipartFile avatar) {
        avatarService.saveAvatarIntoDB(userService.findUserByEmail(userService.getCurrentUser()), avatar);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAvatarsOfUser() {
        return ResponseEntity.ok(avatarService.getAllAvatarsOfUser());
    }

    @DeleteMapping("/delete/{id}")
    public void deletePostById(@PathVariable Long id) {
        avatarService.deleteAvatarById(id);
    }

}