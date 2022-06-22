package com.minboard.service;

import com.minboard.dto.DownloadFileDto;
import com.minboard.dto.BoardFileDto;
import com.minboard.vo.BoardFileVo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

public interface BoardFileService {

    String getFullPath(String filePath);

    List<BoardFileDto> storeFiles(List<MultipartFile> multipartFiles, int boardId) throws IOException;

    void saveBoardFileList(List<BoardFileDto> uploadFileList);

    BoardFileDto storeFile(MultipartFile multipartFile, int boardId) throws IOException;

    String extractExt(String originalFileName);

    List<BoardFileVo> getBoardFileList(int id);

    BoardFileVo getBoardFile(int id);

    boolean fileExtensionInboundCheck(MultipartFile multipartFile) throws IOException;

    void deleteFile(int id);
    
    DownloadFileDto downloadBoardFile(int fileId) throws MalformedURLException;

}
