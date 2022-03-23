package com.minboard.mapper;

import com.minboard.dto.UploadFileDto;
import com.minboard.vo.UploadFileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadFileMapper {

    void insertFileList(List<UploadFileVo> uploadFileList);

    List<UploadFileDto> getUploadFileList(int id);
}