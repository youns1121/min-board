package com.minboard.service;

import com.minboard.vo.BoardFileUploadVo;
import com.minboard.vo.BoardSaveVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface BoardUploadService {

    void saveBoardFile(BoardFileUploadVo boardFileUploadVo, MultipartFile multipartFile) throws IOException;

}
