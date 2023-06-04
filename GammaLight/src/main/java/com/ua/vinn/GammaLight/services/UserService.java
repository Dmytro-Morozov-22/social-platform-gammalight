package com.ua.vinn.GammaLight.services;

import com.ua.vinn.GammaLight.dto.request.AuthenticationUserRequestDto;
import com.ua.vinn.GammaLight.dto.request.RegisterUserRequestDto;
import com.ua.vinn.GammaLight.dto.response.AvailableUserResponseDto;
import com.ua.vinn.GammaLight.dto.response.ResponseDto;
import com.ua.vinn.GammaLight.dto.response.UserTokenResponseDto;
import com.ua.vinn.GammaLight.models.User;
import com.ua.vinn.GammaLight.models.securityElements.Role;
import com.ua.vinn.GammaLight.models.securityElements.Status;
import com.ua.vinn.GammaLight.repositories.UserRepository;
import com.ua.vinn.GammaLight.securities.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenProvider jwtTokenProvider;
    private final AvatarService avatarService;

    @Autowired
    public UserService(AuthenticationManager authenticationManager, @Lazy JwtTokenProvider jwtTokenProvider, UserRepository userRepository, PasswordEncoder encoder, AvatarService avatarService) {
        this.userRepository = userRepository;
        this.encoder = encoder;
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.avatarService = avatarService;
    }

    public ResponseDto saveUser(RegisterUserRequestDto userRequestDto) {
        if(isEmailUnique(userRequestDto.getEmail())){
            User savedUser = userRepository.save(
                new User(userRequestDto.getFirstName(),
                    userRequestDto.getEmail(),
                    encoder.encode(userRequestDto.getPassword()),
                    appropriateRole(userRequestDto.getEmail(), userRequestDto.getPassword()),
                    Status.ACTIVE
                )
            );
            if (userRequestDto.getAvatar().getSize() > 0)
                avatarService.saveAvatarIntoDB(savedUser, userRequestDto.getAvatar());
            return new ResponseDto(savedUser);
        }
        throw new ResponseStatusException(420, "The" + userRequestDto.getEmail() + "is already in the system" , null);
    }

    public User findUserByEmail(String userEmail) {
        return userRepository.findByEmail(userEmail)
            .orElseThrow(() ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,"User with email '" +userEmail+"' was not found"));
    }

    public String getCurrentUser(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    public User getUserFromContext(){
        return findUserByEmail(getCurrentUser());
    }

    public boolean isEmailUnique(String email){
        return userRepository.findByEmail(email).isEmpty();
    }

    /**
     *Отже за допомогою authenticationManager аутентифікуємо користувача за mail та password
     * Далі за допомогою userRepository отримуємо користувача з БД, щоб дізнатися та використати його роль.
     * Потім jwtTokenProvider створює на основі токен email та ролі користувача та надсилає цей токен у відповіді.
     */
    public ResponseEntity<?> loginUser(AuthenticationUserRequestDto requestDto){
        try {
//            User user = userRepository.findByEmail(requestDto.getEmail()).orElseThrow(
//                () -> new ResponseStatusException(
//                    HttpStatus.NOT_FOUND,"User with email '" + requestDto.getEmail() + "' doesn't exist"));

            User user = findUserByEmail(requestDto.getEmail());

            authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(requestDto.getEmail(), requestDto.getPassword()));

            String token = jwtTokenProvider.createToken(requestDto.getEmail(), user.getRole().name());

            return ResponseEntity.ok(new UserTokenResponseDto(requestDto.getEmail(), token));
        } catch (AuthenticationException e){
            throw new ResponseStatusException(409,"The password " + requestDto.getPassword() + " is wrong", e);
        }
    }

    public void logoutUser(HttpServletRequest request, HttpServletResponse response){
        SecurityContextLogoutHandler securityContextLogoutHandler = new SecurityContextLogoutHandler();
        securityContextLogoutHandler.logout(request, response, null);
    }

    public List<AvailableUserResponseDto> getAllUsers(){
        List<AvailableUserResponseDto> allUsers = new ArrayList<>();
        List<User> firstName = userRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
        firstName.forEach(x ->  allUsers.add(new AvailableUserResponseDto(x.getId(), x.getFirstName())));
        return allUsers;
    }

    public void isUserInDataBase(long id){
        if(!userRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no user with ID "+ id +" in the system");
        }
    }

    private Role appropriateRole(String email, String password) {
        if(email.equals("admin@gmail.com") && password.equals("10admin10")) return Role.ADMINISTRATOR;
        if(email.equals("moderator@gmail.com") && password.equals("01moderator01")) return Role.MODERATOR;
        return Role.USER;
    }

}//UserService