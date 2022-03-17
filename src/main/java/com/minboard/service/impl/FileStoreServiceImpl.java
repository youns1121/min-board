package com.minboard.service.impl;

import com.minboard.service.FileStoreService;
import com.minboard.vo.UploadFileVo;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Log
@Service
public class FileStoreServiceImpl implements FileStoreService {

    @Value("{uploadPath}")
    private String uploadPath;

    @Override
    public String getFullPath(String fileName) {
        return uploadPath + fileName;
    }

    @Override
    public List<UploadFileVo> storeFiles(List<MultipartFile> multipartFiles) throws IOException {

        List<UploadFileVo> storeFileResult = new ArrayList<>();
        for (MultipartFile mutipartFile : multipartFiles) {
            if(!multipartFiles.isEmpty()){
                storeFileResult.add(storeFile(mutipartFile));
            }
        }
        return storeFileResult;
    }

    @Override
    public UploadFileVo storeFile(MultipartFile multipartFile) throws IOException {

        if(multipartFile.isEmpty()){
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String storeFileName = createStoreFileName(originalFilename);
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return UploadFileVo.builder()
                .originalFileName(originalFilename)
                .storeFileName(storeFileName)
                .build();

    }

    @Override
    public String createStoreFileName(String originalFileName) {
        String ext = extractExt(originalFileName);
        String uuid = UUID.randomUUID().toString();
        return uuid + "." + ext;
    }

    @Override
    public String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos+1);

    }

}
