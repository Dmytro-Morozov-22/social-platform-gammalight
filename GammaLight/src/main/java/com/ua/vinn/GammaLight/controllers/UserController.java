package com.ua.vinn.GammaLight.controllers;

import com.ua.vinn.GammaLight.dto.request.AuthenticationUserRequestDto;
import com.ua.vinn.GammaLight.dto.request.RegisterUserRequestDto;
import com.ua.vinn.GammaLight.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> userRegister(@ModelAttribute RegisterUserRequestDto userRequestDto) {
        return ResponseEntity.ok(userService.saveUser(userRequestDto));
    }

    @PostMapping("/login")
    public ResponseEntity<?> userAuthenticate(@RequestBody AuthenticationUserRequestDto requestDto) {
        return userService.loginUser(requestDto);
    }

    @GetMapping("/logout")
    public void userLogout(HttpServletRequest request, HttpServletResponse response) {
        userService.logoutUser(request, response);
    }

    @GetMapping("/all")
    public ResponseEntity<?> getAllUsersInSystem(){
        return ResponseEntity.ok(userService.getAllUsers());
    }

}
