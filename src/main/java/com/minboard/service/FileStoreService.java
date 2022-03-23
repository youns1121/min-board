package com.minboard.service;

import com.minboard.dto.UploadFileDto;
import com.minboard.vo.UploadFileVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileStoreService {

    String getFullPath(String filePath);

    List<UploadFileVo> storeFiles(List<MultipartFile> multipartFiles, int boardId) throws IOException;

    UploadFileVo storeFile(MultipartFile multipartFile, int boardId) throws IOException;

    String createStoreFileName(String fileNameOri);

    String extractExt(String originalFileName);

    List<UploadFileDto> getUploadFileList(int id);
}
