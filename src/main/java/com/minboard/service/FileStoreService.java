package com.minboard.service;

import com.minboard.vo.UploadFileVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileStoreService {

    String getFullPath(String fileName);

    List<UploadFileVo> storeFiles(List<MultipartFile> multipartFiles) throws IOException;

    UploadFileVo storeFile(MultipartFile multipartFile) throws IOException;

    String createStoreFileName(String fileNameOri);

    String extractExt(String originalFileName);



}
