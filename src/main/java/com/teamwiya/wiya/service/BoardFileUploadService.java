package com.teamwiya.wiya.service;

import lombok.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class BoardFileUploadService {

    /*
    @Value("${user.home}")
    //file.upload.directory = uploadFiles 폴더 위치
    //출처: https://private.tistory.com/64 [오토봇팩토리:티스토리]??????
    private String uploadDir;

    public void fileUpload(MultipartFile multipartFile){
        Path copyOfLocation = Paths.get(uploadDir+ File.separator+ StringUtils.cleanPath(multipartFile.getOriginalFilename()));
        try {
            Files.copy(multipartFile.getInputStream(),copyOfLocation, StandardCopyOption.REPLACE_EXISTING);
        }catch (IOException e){
            //throw new FileStorageException("fail to store file");
        }
    }
    */

}
