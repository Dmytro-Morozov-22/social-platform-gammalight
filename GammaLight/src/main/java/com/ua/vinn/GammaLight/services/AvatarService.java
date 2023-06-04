package com.ua.vinn.GammaLight.services;

import com.ua.vinn.GammaLight.models.Avatar;
import com.ua.vinn.GammaLight.models.User;
import com.ua.vinn.GammaLight.repositories.AvatarRepository;
import com.ua.vinn.GammaLight.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.File;
import java.time.LocalDate;
import java.util.List;

@Service
public class AvatarService {
    private final AvatarRepository avatarRepository;
    private final UserService userService;
    private final Environment environment;

    @Autowired
    public AvatarService(AvatarRepository avatarRepository, @Lazy UserService userService, Environment environment) {
        this.avatarRepository = avatarRepository;
        this.userService = userService;
        this.environment = environment;
    }

    public Avatar getAvatarById(Long avatarId){
        return avatarRepository.findById(avatarId)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,"Avatar with id '" +  avatarId + "' does not found"));
    }

    public void saveAvatarIntoDB( User user, MultipartFile avatar){
        final String TARGET_FOLDER = "userAvatars";
        avatarRepository.save(new Avatar(FileUtil.savePictureToDB(avatar, TARGET_FOLDER,user.getEmail()),true, LocalDate.now(), user));
    }




    @Deprecated(forRemoval = true)
    public String saveAvatarInDirectory(MultipartFile avatar){

        String userAvatar = "C:/Users/User/Desktop/Java/Gamma/usersImages/defaultAvatar/defaultAvatar.png";

        if (!avatar.isEmpty()) {

            String fileName = FileUtil.getFileName(avatar.getOriginalFilename());
            String filePath = "C:/Users/User/Desktop/Java/Gamma/usersImages/"+userService.getCurrentUser()+"/userAvatars/";

            try {
                FileUtil.uploadFile(avatar.getBytes(), filePath, fileName);
            } catch (Exception e) {
                e.printStackTrace();
            }
            userAvatar = filePath + fileName;
        }
        return userAvatar;
    }

    public List<Avatar> getAllAvatarsOfUser(){
        return userService.findUserByEmail(userService.getCurrentUser()).getAvatar();
    }

    public void deleteAvatarById(Long id) {

        Avatar avatar = avatarRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Avatar with id '"+id+"' was not found"));

        if(userService.findUserByEmail(userService.getCurrentUser()).getId() == avatar.getUser().getId()){
            new File(avatar.getAvatarLink()).delete();
            avatarRepository.deleteById(id);
        } else {
            throw new ResponseStatusException(
                HttpStatus.FORBIDDEN,"Access to the avatar with id '" + id + "' is forbidden");
        }
    }
}
