package com.minboard.mapper;

import com.minboard.vo.BoardFileUploadVo;
import org.apache.ibatis.annotations.Mapper;

import java.io.IOException;

@Mapper
public interface FileMapper {

    void save(BoardFileUploadVo boardFileUploadVo);

    String uploadFile(String uploadPath, String fileNameOri, byte[] fileData);

    void deleteFile(String filePath);

}
