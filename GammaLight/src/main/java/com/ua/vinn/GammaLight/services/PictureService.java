package com.ua.vinn.GammaLight.services;

import com.ua.vinn.GammaLight.models.Picture;
import com.ua.vinn.GammaLight.models.Post;
import com.ua.vinn.GammaLight.repositories.PictureRepository;
import com.ua.vinn.GammaLight.utils.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Service
public class PictureService {

    @Value("${file.storage.prefix}")
    private String image;

    private final PictureRepository pictureRepository;
    private final UserService userService;

    private final Environment environment;

    @Autowired
    public PictureService(PictureRepository pictureRepository, UserService userService, Environment environment) {
        this.pictureRepository = pictureRepository;
        this.userService = userService;
        this.environment = environment;
    }

    public void savePicture(Post savedPost, MultipartFile picture, String targetFolder) {
        String userEmail = userService.getCurrentUser();
        pictureRepository.save(new Picture(FileUtil.savePictureToDB(picture, targetFolder, userEmail), savedPost));
    }


    private String savePictureToDB(MultipartFile picture, String targetFolder){
        String fileName = FileUtil.getFileName(picture.getOriginalFilename());
        String port = environment.getProperty("server.port");
        String hostname = "localhost";
        savePictureInDirectory(picture, fileName, targetFolder);
        return String.format("http://%s:%s/%s/%s/%s/%s",hostname,port,image,userService.getCurrentUser(),targetFolder,fileName);
    }

    private void savePictureInDirectory(MultipartFile picture, String fileName, String targetFolder){
        String filePath = "C:/Users/User/Desktop/Java/Gamma/usersImages/"+userService.getCurrentUser()+"/"+targetFolder+"/";
        try {
            FileUtil.uploadFile(picture.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    public void deletePictureFromDirectory(Long postId) throws IOException {
        List<Picture> pictures = pictureRepository.getPictureByPostId(postId);
        for(Picture picture: pictures)
            Files.deleteIfExists(Paths.get(getLocalPhotoLink(picture.getPictureLink())));
    }

    private String getLocalPhotoLink(String link){
        String partLocalLink = "C:/Users/User/Desktop/Java/Gamma/usersImages/";
        return partLocalLink.concat(link.substring(28));
    }


}
