package com.minboard.service.impl;

import com.minboard.dto.DownloadFileDto;
import com.minboard.dto.BoardFileDto;
import com.minboard.enums.ServiceMessageEnums;
import com.minboard.mapper.BoardFileMapper;
import com.minboard.service.BoardFileService;
import com.minboard.vo.BoardFileVo;
import lombok.RequiredArgsConstructor;
import org.apache.tika.Tika;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.util.UriUtils;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.*;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class BoardFileServiceImpl implements BoardFileService {

    private final BoardFileMapper boardFileMapper;

    @Value("${custom.path.uploadPath}")
    private String uploadPath;

    @Override
    public String getFullPath(String fileName) {
        return uploadPath + fileName;
    }

    @Override
    public List<BoardFileDto> storeFiles(List<MultipartFile> multipartFiles, int boardId) throws IOException {

        List<BoardFileDto> boardFileResult = Collections.emptyList();

        if(!CollectionUtils.isEmpty(multipartFiles)) {

            boardFileResult = new ArrayList<>();

            for (MultipartFile multipartFile : multipartFiles) {
                boardFileResult.add(storeFile(multipartFile, boardId));
            }
        }

        return boardFileResult;
    }

    @Transactional
    @Override
    public void saveBoardFileList(List<BoardFileDto> boardFileDtoList) {
        boardFileMapper.insertBoardFileList(boardFileDtoList);
    }

    @Override
    public BoardFileDto storeFile(MultipartFile multipartFile, int boardId)
            throws IOException {

        if (!fileExtensionInboundCheck(multipartFile)){
            throw new IllegalArgumentException(ServiceMessageEnums.NO_DATA.getKey());
        }

        String originalFilename = multipartFile.getOriginalFilename();
        String extensionName = extractExt(originalFilename);
        String storeFileName = UUID.randomUUID().toString();

        multipartFile.transferTo(new File(getFullPath(storeFileName + "." + extensionName)));

        return BoardFileDto.builder()
                .originalFileName(originalFilename)
                .storeFileName(storeFileName)
                .extensionName(extensionName)
                .storeFileSize(multipartFile.getSize())
                .storeFilePath(uploadPath)
                .boardId(boardId)
                .build();
    }

    @Override
    public List<BoardFileVo> getBoardFileList(int id) {

        return boardFileMapper.selectBoardFileList(id);
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

        if(permitImgMimeType.contains(new Tika().detect(inputStream).toLowerCase(Locale.ROOT))) {
            return true;
        }
        return false;
    }


    @Override
    public BoardFileVo getBoardFile(int id) {

        return boardFileMapper.selectBoardFile(id);
    }

    @Override
    public String extractExt(String originalFileName) {

        return originalFileName.substring(originalFileName.lastIndexOf(".") + 1)
                .toLowerCase(Locale.ROOT);
    }

    @Transactional
    @Override
    public void deleteFile(int id) {
        BoardFileVo boardFileVo = boardFileMapper.selectBoardFile(id);
        File file = new File(uploadPath + boardFileVo.getStoreFileName() );
        if(file.exists()){
            file.delete();
        }
        boardFileMapper.deleteBoardFile(id);

    }

    @Override
    public DownloadFileDto downloadBoardFile(int fileId) throws MalformedURLException {
        BoardFileVo uploadFile = boardFileMapper.selectBoardFile(fileId);
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
