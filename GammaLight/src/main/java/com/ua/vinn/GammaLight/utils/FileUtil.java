package com.ua.vinn.GammaLight.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

public class FileUtil {

    @Value("${file.storage.prefix}")
    private static String image;

    @Value("${server.port}")
    private static String port;


    public static void uploadFile(byte[] file,String filePath,String fileName)throws Exception{
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        FileOutputStream out = new FileOutputStream(filePath+fileName);
        out.write(file);
        out.flush();
        out.close();
    }

    public static String getSuffix(String fileName){
        return fileName.substring(fileName.lastIndexOf("."));
    }

    public static String getFileName(String fileOriginName){
        return getUUID() + getSuffix(fileOriginName);
    }

    public static String getUUID(){
        return UUID.randomUUID().toString();//.replace("-","");
    }

    public static String savePictureToDB(MultipartFile picture, String targetFolder, String userEmail){
        String fileName = getFileName(picture.getOriginalFilename());
        String hostname = "localhost";
        savePictureInDirectory(picture, targetFolder, userEmail);
        return String.format("http://%s:%s/%s/%s/%s/%s",hostname,port,image,userEmail,targetFolder,fileName);
    }

    private static void savePictureInDirectory(MultipartFile picture, String targetFolder, String userEmail){
        String filePath = "C:/Users/User/Desktop/Java/Gamma/usersImages/"+userEmail+"/"+targetFolder+"/";
        String fileName = getFileName(picture.getOriginalFilename());
        try {
            FileUtil.uploadFile(picture.getBytes(), filePath, fileName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }




}
