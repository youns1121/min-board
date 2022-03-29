package com.minboard.service.impl;

import com.minboard.dto.UploadFileDto;
import com.minboard.mapper.UploadFileMapper;
import com.minboard.service.FileStoreService;
import com.minboard.vo.UploadFileUpdateVo;
import com.minboard.vo.UploadFileVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Slf4j
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
        boolean retVal = false;
        List<UploadFileVo> storeFileResult = new ArrayList<>();
        for (MultipartFile mutipartFile : multipartFiles) {
            if(!multipartFiles.isEmpty()){
                UploadFileVo filevo = storeFile(mutipartFile, boardId);
                if(filevo != null) {
                    storeFileResult.add(filevo);
                    retVal = true;
                }
            }
        }

        if(!retVal){
            return null;
        }else{
            return storeFileResult;
        }

    }

    @Override
    public List<UploadFileUpdateVo> storeFilesUpdate(List<MultipartFile> multipartFilesUpdate, int boardId) throws IOException {
        boolean retVal = false;
        List<UploadFileUpdateVo> storeFileResult = new ArrayList<>();
        for (MultipartFile mutipartFile : multipartFilesUpdate) {
            if(!multipartFilesUpdate.isEmpty()){
                UploadFileUpdateVo uploadFileUpdate = storeFileUpdate(mutipartFile, boardId);
                if(uploadFileUpdate != null) {
                    storeFileResult.add(uploadFileUpdate);
                    retVal = true;
                }
            }
        }

        if(!retVal){
            return null;
        }else{
            return storeFileResult;
        }
    }

    @Override
    public UploadFileUpdateVo storeFileUpdate(MultipartFile multipartFileUpdate, int boardId) throws IOException {
        if(multipartFileUpdate.isEmpty()){
            return null;
        }

        String originalFilename = multipartFileUpdate.getOriginalFilename();
        String extensionName = extractExt(originalFilename);
        String storeFileName = createStoreFileName(originalFilename);
        long fileSize = multipartFileUpdate.getSize();
        multipartFileUpdate.transferTo(new File(getFullPath(storeFileName)));
        if(fileSize == 0){
            return  null;
        }
        return UploadFileUpdateVo.builder()
                .originalFileName(originalFilename)
                .extensionName(extensionName)
                .storeFileName(storeFileName)
                .storeFileSize(fileSize)
                .storeFilePath(uploadPath)
                .boardId(boardId)
                .build();

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
        if(fileSize == 0){
            return  null;
        }
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
    public List<UploadFileUpdateVo> getUploadFileUpdateList(int id) {
        List<UploadFileUpdateVo> uploadFileUpdateList = uploadFileMapper.getUploadFileUpdateList(id);
        return uploadFileUpdateList;
    }

    @Override
    public UploadFileDto findByUploadFile(int id) {
        UploadFileDto uploadFile = uploadFileMapper.findByUploadFile(id);
        return uploadFile;
    }

    @Override
    public void deleteFile(int id) {
        UploadFileDto uploadFile = uploadFileMapper.findByUploadFile(id);
        String storeFileName = uploadFile.getStoreFileName();
        File file = new File(uploadPath + storeFileName );
        if(file.exists()){
            file.delete();
        }
        uploadFileMapper.deleteFile(id);

    }
}
