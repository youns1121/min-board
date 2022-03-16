package com.minboard.service.impl;

import com.minboard.service.FileService;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@Log
@Service
public class FileServiceImpl implements FileService {

    @Override
    public String uploadFile(String uploadPath, String fileNameOri, byte[] fileData) throws IOException {

        UUID uuid = UUID.randomUUID();
        String fileExtensionName = fileNameOri.substring(fileNameOri.lastIndexOf("."));
        String savedFileName = uuid + fileExtensionName;
        String fileUploadFullUrl = uploadPath + "/" + savedFileName;
        FileOutputStream fos = new FileOutputStream(fileUploadFullUrl);
        fos.write(fileData);
        fos.close();

        return savedFileName;
    }

    @Override
    public void deleteFile(String filePath) {

    }
}
