package com.ua.vinn.GammaLight.securities;

import com.ua.vinn.GammaLight.repositories.UserRepository;
import com.ua.vinn.GammaLight.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Service("userDetailsServiceImpl")
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserService userService;
    private final UserRepository userRepository;

    @Autowired
    public UserDetailsServiceImpl(UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) {
//        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new UserNotFoundAndStatusException("User was not found", HttpStatus.NOT_FOUND));

        return SecurityUser.turnUserIntoUserDetails(userService.findUserByEmail(userEmail));

//        return SecurityUser.turnUserIntoUserDetails(user);
    }
}
