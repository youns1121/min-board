package com.minboard.mapper;

import com.minboard.vo.UploadFileVo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UploadFileMapper {

    void insertFileList(UploadFileVo boardFileUploadVo);

    String uploadFile(String uploadPath, String fileNameOri, byte[] fileData);

    void deleteFile(String filePath);

}
