package com.minboard.mapper;

import com.minboard.dto.UploadFileDto;
import com.minboard.vo.UploadFileUpdateVo;
import com.minboard.vo.UploadFileVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UploadFileMapper {

    void insertFileList(List<UploadFileVo> uploadFileList);

    void updateFileList(List<UploadFileUpdateVo> uploadFileList);

    void getDetailViewUpdateUploadFile(List<UploadFileUpdateVo> uploadFileUpdateVo);

    UploadFileDto findByUploadFile(int id);

    List<UploadFileDto> getUploadFileList(int id);

    List<UploadFileUpdateVo> getUploadFileUpdateList(int id);

    void deleteFile(int id);



}
