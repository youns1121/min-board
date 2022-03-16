package com.minboard.service;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface FileService {

    String uploadFile(String uploadPath, String fileNameOri, byte[] fileData ) throws IOException;

    void deleteFile(String filePath);
}
