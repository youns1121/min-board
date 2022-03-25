package com.minboard.service.impl;

import com.minboard.dto.UploadFileDto;
import com.minboard.mapper.UploadFileMapper;
import com.minboard.service.FileStoreService;
import com.minboard.vo.UploadFileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

@Log
@Service
@RequiredArgsConstructor
public class FileStoreServiceImpl implements FileStoreService {

    private final UploadFileMapper uploadFileMapper;

    @Value("${custom.path.uploadPath}")
    private String uploadPath;

    @Override
    public String getFullPath(String fileName) {
        return uploadPath + fileName;
    }

    @Override
    public List<UploadFileVo> storeFiles(List<MultipartFile> multipartFiles, int boardId) throws IOException {

        List<UploadFileVo> storeFileResult = new ArrayList<>();
        for (MultipartFile mutipartFile : multipartFiles) {
            if(!multipartFiles.isEmpty()){
                storeFileResult.add(storeFile(mutipartFile, boardId));
            }
        }
        return storeFileResult;
    }

    @Override
    public UploadFileVo storeFile(MultipartFile multipartFile, int boardId) throws IOException {

        if(multipartFile.isEmpty()){
            return null;
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String extensionName = extractExt(originalFilename);
        String storeFileName = createStoreFileName(originalFilename);
        long fileSize = multipartFile.getSize();
        multipartFile.transferTo(new File(getFullPath(storeFileName)));

        return UploadFileVo.builder()
                .originalFileName(originalFilename)
                .storeFileName(storeFileName)
                .extensionName(extensionName)
                .storeFileSize(fileSize)
                .storeFilePath(uploadPath)
                .boardId(boardId)
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
        return originalFileName.substring(pos + 1);
    }

    @Override
    public List<UploadFileDto> getUploadFileList(int id) {
        List<UploadFileDto> uploadFileList = uploadFileMapper.getUploadFileList(id);
        return uploadFileList;
    }

    @Override
    public UploadFileDto findByUploadFile(int id) {
        UploadFileDto uploadFile = uploadFileMapper.findByUploadFile(id);
        return uploadFile;
    }
}
