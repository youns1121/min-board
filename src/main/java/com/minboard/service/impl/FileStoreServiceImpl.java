package com.minboard.service.impl;

import com.minboard.dto.DownloadFileDto;
import com.minboard.dto.UploadFileDto;
import com.minboard.mapper.UploadFileMapper;
import com.minboard.service.FileStoreService;
import com.minboard.vo.UploadFileUpdateVo;
import com.minboard.vo.UploadFileVo;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class FileStoreServiceImpl implements FileStoreService {

    private final UploadFileMapper uploadFileMapper;

    @Value("${custom.path.uploadPath}")
    private String uploadPath;

    /** 파일 전체경로 가져오기 **/
    @Override
    public String getFullPath(String fileName) {
        return uploadPath + fileName;
    }

    /** 파일리스트 저장하기 **/
    @Override
    public List<UploadFileVo> storeFiles(List<MultipartFile> multipartFiles, int boardId) throws IOException {

        List<UploadFileVo> storeFileResult = new ArrayList<>();
        for (MultipartFile multipartFile : multipartFiles) {
            UploadFileVo uploadFileInfo = storeFile(multipartFile, boardId);
            if(uploadFileInfo == null){
                return null;
            }
            storeFileResult.add(uploadFileInfo);
        }
        return storeFileResult;
    }

    /** 파일업로드 리스트 정보 입력하기 **/
    @Override
    public void insertFileInfoList(List<UploadFileVo> uploadFileInfo) {
        uploadFileMapper.insertFileInfoList(uploadFileInfo);
    }

    @Override
    public void updateFileInfoList(List<UploadFileUpdateVo> uploadFileList) {
        uploadFileMapper.updateFileInfoList(uploadFileList);
    }

    /** 단일파일 저장하기 **/
    @Override
    public UploadFileVo storeFile(MultipartFile multipartFile, int boardId) throws IOException {

        boolean fileCheck = fileExtensionInboundCheck(multipartFile);
        if (!fileCheck){
            return null;
        }
        String originalFilename = multipartFile.getOriginalFilename();
        String extensionName = extractExt(originalFilename);
        String storeFileName = createStoreFileName();
        long fileSize = multipartFile.getSize();
        multipartFile.transferTo(new File(getFullPath(storeFileName + "." + extensionName)));
        return UploadFileVo.builder()
                .originalFileName(originalFilename)
                .storeFileName(storeFileName)
                .extensionName(extensionName)
                .storeFileSize(fileSize)
                .storeFilePath(uploadPath)
                .boardId(boardId)
                .build();
    }

    /** 파일리스트 수정하기 **/
    @Override
    public List<UploadFileUpdateVo> storeFilesUpdate(List<MultipartFile> multipartFilesUpdate, int boardId) throws IOException {

        List<UploadFileUpdateVo> storeFileResult = new ArrayList<>();
        for (MultipartFile mutipartFile : multipartFilesUpdate) {
            UploadFileUpdateVo uploadFileInfo = storeFileUpdate(mutipartFile, boardId);
            if(uploadFileInfo == null){
                return null;
            }
            storeFileResult.add(uploadFileInfo);
        }

        return storeFileResult;
    }

    /** 단일파일 수정하기 **/
    @Override
    public UploadFileUpdateVo storeFileUpdate(MultipartFile multipartFileUpdate, int boardId) throws IOException {

        boolean fileCheck = fileExtensionInboundCheck(multipartFileUpdate);
        if (!fileCheck){
            return null;
        }
        String originalFilename = multipartFileUpdate.getOriginalFilename();
        String extensionName = extractExt(originalFilename);
        String storeFileName = createStoreFileName();
        long fileSize = multipartFileUpdate.getSize();
        multipartFileUpdate.transferTo(new File(getFullPath(storeFileName + "." + extensionName)));
        return UploadFileUpdateVo.builder()
                .originalFileName(originalFilename)
                .storeFileName(storeFileName)
                .extensionName(extensionName)
                .storeFileSize(fileSize)
                .storeFilePath(uploadPath)
                .boardId(boardId)
                .build();
    }

    /** 업로드한 파일리스트 가져오기 **/
    @Override
    public List<UploadFileDto> getUploadFileList(int id) {
        List<UploadFileDto> uploadFileList = uploadFileMapper.getUploadFileList(id);
        return uploadFileList;
    }

    /** 업데이트할 파일리스트 가져오기 **/
    @Override
    public List<UploadFileUpdateVo> getUploadFileUpdateList(int id) {
        List<UploadFileUpdateVo> uploadFileUpdateList = uploadFileMapper.getUploadFileUpdateList(id);
        return uploadFileUpdateList;
    }

    @Override
    public boolean fileExtensionInboundCheck(MultipartFile multipartFile) throws IOException {

        List<String> permitImgMimeType = Arrays.asList(
                "application/zip", "application/pdf", "application/msword",
                "text/plain",
                "application/x-hwp", "applicaion/haansofthwp", "application/x-tika-msoffice", // .hwp
                "application/x-tika-ooxml",  // .xlsx, .pptx, .docx\
                "image/jpeg", "image/gif", "image/png", // 이미지
                "application/vnd.ms-word",          // .docx 등 워드 관련
                "application/vnd.ms-excel",         // .xls 등 엑셀 관련
                "application/vnd.ms-powerpoint",    // .ppt 등 파워포인트 관련
                "application/vnd.openxmlformats-officedocument",    // .docx, .dotx, .xlsx, .xltx, .pptx, .potx, .ppsx
                "applicaion/vnd.hancom"     // .hwp 관련
        );
        InputStream inputStream = multipartFile.getInputStream();
        String mimeType = new Tika().detect(inputStream);

        if (!permitImgMimeType.contains(mimeType.toLowerCase(Locale.ROOT))) {
            return false;
        }
        return true;
    }

    /** 파일정보 가져오기 **/
    @Override
    public UploadFileDto findByUploadFile(int id) {
        UploadFileDto uploadFile = uploadFileMapper.findByUploadFile(id);
        return uploadFile;
    }

    /** 파일이름 생성하기 **/
    @Override
    public String createStoreFileName() {
        String uuid = UUID.randomUUID().toString();
        return uuid;
    }

    /** 파일확장자 추출하기 **/
    @Override
    public String extractExt(String originalFileName) {
        int pos = originalFileName.lastIndexOf(".");
        return originalFileName.substring(pos + 1).toLowerCase(Locale.ROOT);
    }

    /** 단일파일 삭제하기 **/
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

    /** 첨부파일 다운로드 **/
    @Override
    public DownloadFileDto downloadAttachedFile(int fileId) throws MalformedURLException {
        UploadFileDto uploadFile = uploadFileMapper.findByUploadFile(fileId);
        String storeFileName = uploadFile.getStoreFileName() + "." + uploadFile.getExtensionName();
        String uploadFileName = uploadFile.getOriginalFileName();

        UrlResource resource = new UrlResource("file:" + getFullPath(storeFileName));
        String encodedUploadFileName = UriUtils.encode(uploadFileName, StandardCharsets.UTF_8);
        String contentDisposition = "attachment; filename=\"" + encodedUploadFileName + "\"";

        return DownloadFileDto.builder()
                .resource(String.valueOf(resource))
                .contentDisposition(contentDisposition)
                .build();
    }
}
