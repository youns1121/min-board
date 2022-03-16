package com.minboard.service.impl;

import com.minboard.mapper.FileMapper;
import com.minboard.service.BoardUploadService;
import com.minboard.service.FileService;
import com.minboard.vo.BoardFileUploadVo;
import com.minboard.vo.BoardSaveVo;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class BoardUploadServiceImpl implements BoardUploadService {

    @Value("${uploadPathOri}")
    private String uploadPathOri;

    private final FileService fileService;

    private final FileMapper fileMapper;

    @Override
    public void saveBoardFile(BoardFileUploadVo boardFileUploadVo, MultipartFile multipartFile) throws IOException {

        String fileNameOri = boardFileUploadVo.getFileNameOri();
        String fileName = "";
        String fileUrl = "";

        //파일 업로드
        if(!StringUtils.isEmpty(fileNameOri)){
            fileName = fileService.uploadFile(uploadPathOri, fileNameOri, multipartFile.getBytes());
            fileUrl = "/images/board/" + fileName;
        }

        boardFileUploadVo.updateBoardFile(fileNameOri, fileName, fileUrl);

        fileMapper.save(boardFileUploadVo);
                
    }
}
